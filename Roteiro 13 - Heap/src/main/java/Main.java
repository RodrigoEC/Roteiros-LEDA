import adt.heap.HeapImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {16, 10, 14, 7, 8, 6, 5};
        Integer[] array1 = {1, 11, 14, 9, 3, 8, 2};

        HeapImpl<Integer> b = new HeapImpl<>((o1, o2) -> o1 - o2);
        b.buildHeap(array);
        b.heapify(0);


        System.out.println(Arrays.toString(b.elementsByLevel(2)));

    }
}
