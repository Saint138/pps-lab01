package tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private int pin;
    private int attempts;
    private boolean isPinSet;
    private boolean isLocked;
    private boolean isBlocked;
    public static final int MAX_ATTEMPTS = 3;

    public SmartDoorLockImpl() {
      this.reset();
    }

    @Override
    public void setPin(int pin) {
        if(isLocked()){
            throw new IllegalArgumentException("Locked, cannot set pin");
        }else {
            this.pin = pin;
            this.isPinSet = true;
        }
    }

    @Override
    public void unlock(int pin) {
        if(isBlocked()){
            throw new IllegalArgumentException("Blocked, cannot unlock");
        }else {
            if(this.pin == pin){
                isLocked = false;
                attempts=0;
            } else {
                handleWrongPin();
            }
        }
    }

    @Override
    public void lock() {
        if(!this.isPinSet){
            throw new IllegalArgumentException("Pin not set");
        } else {
            this.isLocked = true;
        }
    }

    @Override
    public boolean isLocked() {
        return this.isLocked;
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
    }

    @Override
    public int getMaxAttempts() {
        return this.MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return attempts;
    }

    @Override
    public void reset() {
        this.isPinSet=false;
        this.attempts=0;
        this.isLocked=false;
        this.isBlocked =false;
    }

    @Override
    public int getPin() {
        return this.pin;
    }

    private void handleWrongPin() {
        attempts++;
        if(this.attempts>=MAX_ATTEMPTS){
            isBlocked = true;
            throw new IllegalArgumentException("Blocked");
        }else {
            throw new IllegalArgumentException("Wrong Pin");
        }
    }
}
