package adt.queue;

import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class Main {
    public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException, StackOverflowException, StackUnderflowException {
        StackImpl<Integer> queue3 = new StackImpl<>(3);


        queue3.push(23);
        queue3.push(2);
        queue3.push(4);
        queue3.pop();

        System.out.println(queue3.top());
        System.out.println(queue3.isEmpty());
        System.out.println(queue3.isFull());

    }
}
