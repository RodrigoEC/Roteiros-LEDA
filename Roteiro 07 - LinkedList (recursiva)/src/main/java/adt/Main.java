package adt;

import adt.linkedList.RecursiveDoubleLinkedListImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        RecursiveDoubleLinkedListImpl<Integer> s = new RecursiveDoubleLinkedListImpl<>();
        s.insert(5);
        s.insert(1);
        s.insert(7);

        s.remove(7);

        System.out.println(Arrays.toString(s.toArray()));


    }
}
