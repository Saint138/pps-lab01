package tdd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    MinMaxStackImpl stack = new MinMaxStackImpl();
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

    private void pushList(){
        for(Integer i : list) {
            stack.push(i);
        }
    }


    @Test
    void checkEmptyStack() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void checkEmptyStackSize() {
        assertEquals(0,stack.size());
    }

    @Test
    void canPush(){
        pushList();
        assertAll(
                () -> assertEquals(stack.peek(),list.getLast()),
                () -> assertEquals(list.size(),stack.size())
        );
    }

    @Test
    void canPop(){
        this.pushList();
        assertEquals(list.getLast(),stack.pop());
    }

    @Test
    void canPopWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.pop());
    }

    @Test
    void testGetMin() {
        this.pushList();
        assertEquals(list.stream().min(Integer::compareTo).get(), stack.getMin());
    }

    @Test
    void testGetMax(){
        this.pushList();
        assertEquals(list.stream().max(Integer::compareTo).get(),stack.getMax());
    }

    @Test
    void testGetMinWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.getMin());
    }

    @Test
    void testGetMaxWithEmptyStack() {
        assertThrows(IllegalStateException.class, () -> stack.getMax());
    }
}