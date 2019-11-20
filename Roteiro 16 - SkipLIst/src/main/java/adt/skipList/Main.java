package adt.skipList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SkipListImpl<Integer> bls =  new SkipListImpl<>(5);


        bls.insert(1, 1, 3);
        bls.insert(2, 0, 2);
        bls.insert(7, 9, 4);

        System.out.println(Arrays.toString(bls.allNodes()));
    }
}
