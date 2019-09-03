package sorting;

import sorting.linearSorting.CountingSort;
import sorting.linearSorting.ExtendedCountingSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ExtendedCountingSort e = new ExtendedCountingSort();
        Integer[] p = new Integer[] {-4, -90, -3, -4, -3, -5, -1, -4, -15};
        e.sort(p, 0, 8);


        System.out.println(Arrays.toString(p));

    }
}
