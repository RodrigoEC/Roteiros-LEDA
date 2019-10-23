package sorting;

import sorting.divideAndConquer.MergeSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] i = {1,4,2,3,6,5};

        MergeSort<Integer> m = new MergeSort<>();

        m.sort(i);

        System.out.println(Arrays.toString(i));


    }
}
