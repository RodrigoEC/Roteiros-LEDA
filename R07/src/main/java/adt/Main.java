package adt;

import adt.linkedList.RecursiveDoubleLinkedListImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackRecursiveDoubleLinkedListImpl;
import adt.stack.StackUnderflowException;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
        RecursiveDoubleLinkedListImpl<Integer> l = new RecursiveDoubleLinkedListImpl<>();
        StackRecursiveDoubleLinkedListImpl<Integer> db = new StackRecursiveDoubleLinkedListImpl<>(4);

//        l.insertFirst(-98);
//        l.insert(4);
//        l.insert(5);
//        l.insert(8);
//        l.insert(0);
//        l.insert(-1);
//
//
//
//        l.removeFirst();
//        l.removeLast();
//        l.removeLast();
//        l.remove(5);
//
//        System.out.println(Arrays.toString(l.toArray()));

        System.out.println(db.isEmpty());
        db.push(6);
        db.push(1);
        db.push(8);

        db.push(0);
        System.out.println(db.top());

        db.pop();
        System.out.println(db.top());
        System.out.println(db.isFull());





    }
}
