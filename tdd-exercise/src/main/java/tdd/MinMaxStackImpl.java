package tdd;

import java.util.ArrayList;
import java.util.List;

public class MinMaxStackImpl implements MinMaxStack {
    List<Integer> stack;
    MinMaxStackImpl() {
        stack = new ArrayList<>();
    }
    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        if(stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        } else {
            return stack.removeLast();
        }
    }


    @Override
    public int peek() {
        if(!stack.isEmpty()) {
        return stack.getLast();
        } else {
            throw new IllegalStateException("Stack is empty");
        }
    }

    @Override
    public int getMin() {
        if(!stack.isEmpty()) {
            return stack.stream().min(Integer::compare).get();
        }
        throw new IllegalStateException("Stack is empty");    }

    @Override
    public int getMax() {
        if(!stack.isEmpty()) {
            return stack.stream().max(Integer::compare).get();
        }
        throw new IllegalStateException("Stack is empty");
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
}
