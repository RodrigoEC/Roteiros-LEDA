package adt.skipList;

public class Main {
    public static void main(String[] args) {
        SkipListImpl<Integer> bls =  new SkipListImpl<>(5);


        bls.insert(1, 1, 3);
        bls.insert(2, 0, 2);
        bls.insert(7, 9, 4);
        System.out.println(bls.height());
        System.out.println(bls.size());

        bls.remove(1);
        System.out.println(bls.size());

    }
}
