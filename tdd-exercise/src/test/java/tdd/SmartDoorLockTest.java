package tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int INITIAL_PIN = 1234;
    private static final int WRONG_PIN = 5678;
    private final SmartDoorLock smartDoorLock = new SmartDoorLockImpl();

    public SmartDoorLockTest() {
    }

    @Test
    public void testIsLocked() {
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void testSetPin() {
        smartDoorLock.setPin(INITIAL_PIN);
        assertEquals(INITIAL_PIN, smartDoorLock.getPin());
    }

    @Test
    public void testCanUnlock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        smartDoorLock.unlock(INITIAL_PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    public void testCanLock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testGetAttempts() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(1, smartDoorLock.getFailedAttempts()));
    }

    @Test
    public void testGetMaxAttempts() {
        assertEquals(SmartDoorLockImpl.MAX_ATTEMPTS, smartDoorLock.getMaxAttempts());
    }

    @Test
    public void canBlock() {
        smartDoorLock.setPin(INITIAL_PIN);
        smartDoorLock.lock();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN)),
                () -> assertEquals(smartDoorLock.getMaxAttempts(), smartDoorLock.getFailedAttempts()),
                () -> assertTrue(smartDoorLock.isBlocked()),
                () -> assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(INITIAL_PIN))
                );
    }

    @Test
    public void testReset() {
        smartDoorLock.setPin(INITIAL_PIN);
        assertThrows(IllegalArgumentException.class, () -> smartDoorLock.unlock(WRONG_PIN));
        smartDoorLock.reset();
        assertFalse(smartDoorLock.isLocked());
        assertEquals(0,smartDoorLock.getFailedAttempts());
    }

}