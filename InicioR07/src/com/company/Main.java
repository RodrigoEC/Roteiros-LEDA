package com.company;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RecursiveDoubleLinkedListImpl<Integer> l = new RecursiveDoubleLinkedListImpl<>();
        l.insert(4);
        l.insert(2);
        l.insert(1);
        l.removeFirst();

        l.insertFirst(6);
        l.removeLast();
        l.remove(null);
        System.out.println(l.size());
        System.out.println(Arrays.toString(l.toArray()));

    }
}
