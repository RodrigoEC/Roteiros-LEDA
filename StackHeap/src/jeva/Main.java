package jeva;

import jeva.adt.heap.HeapImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {5, 16, 10, 14, 7, 8, 6};

        HeapImpl<Integer> b = new HeapImpl<>((o1, o2) -> o1 - o2);

        b.insert(16);
        b.insert(10);
        b.insert(14);
        b.insert(7);
        b.insert(8);
        b.insert(6);
        b.insert(5);


        ArrayList<Integer> ar = new ArrayList<>();

        for (int i = 0; i < 7; i++ ) {
            ar.add(b.extractRootElement());
        }

        System.out.println(Arrays.toString(ar.toArray()));

    }
}
