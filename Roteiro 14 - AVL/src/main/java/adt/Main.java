package adt;

import adt.avltree.AVLTreeImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        AVLTreeImpl<Integer> avl = new AVLTreeImpl<>();

        avl.insert(55);
        avl.insert(9);
        avl.insert(91);
        avl.insert(12);
        avl.insert(1);
        avl.insert(22);
        avl.insert(4);
        avl.insert(91);
        avl.insert(74);
        avl.insert(0);
        avl.insert(3);
        avl.insert(7);
        avl.insert(88);
        avl.insert(66);

        System.out.println(avl.size());
        System.out.println(Arrays.toString(avl.preOrder()));

        avl.remove(91);

        System.out.println(avl.size());
        System.out.println(Arrays.toString(avl.preOrder()));

        avl.remove(12);

        System.out.println(avl.size());
        System.out.println(Arrays.toString(avl.preOrder()));

        avl.remove(9);

        System.out.println(avl.size());
        System.out.println(Arrays.toString(avl.preOrder()));

        avl.remove(55);

        System.out.println(avl.size());
        System.out.println(Arrays.toString(avl.preOrder()));




    }

}
