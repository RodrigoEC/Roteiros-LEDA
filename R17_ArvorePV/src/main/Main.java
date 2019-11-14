package main;

import main.rbtree.RBTreeImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        RBTreeImpl<Integer> r = new RBTreeImpl<>();
        r.insert(4);
        r.insert(1);
        r.insert(8);
        r.insert(6);
        r.insert(2);

        System.out.println(Arrays.toString(r.rbPreOrder()));
    }
}
