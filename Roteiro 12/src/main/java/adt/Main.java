package adt;

import adt.bst.extended.SortComparatorBSTImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {1,5,2,9,0};
        SortComparatorBSTImpl<Integer> b = new SortComparatorBSTImpl<>(((o1, o2) -> o2 - o1));

        System.out.println(Arrays.toString(b.sort(array)));
        System.out.println(Arrays.toString(b.reverseOrder()));

    }
}
