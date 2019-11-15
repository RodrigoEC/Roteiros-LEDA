import adt.heap.HeapImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] array = {16, 10, 14, 7, 8, 6, 5};

        HeapImpl<Integer> b = new HeapImpl<>((o1, o2) -> o1 - o2);
        b.buildHeap(array);
        b.heapify(0);
        ArrayList<Integer> ar = new ArrayList<>();

        for (Integer e: array) {
            ar.add(b.extractRootElement());
        }

        System.out.println(Arrays.toString(ar.toArray()));
        b.toArray();

    }
}
