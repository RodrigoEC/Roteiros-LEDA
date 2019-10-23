package adt;

import adt.bst.BSTImpl;

public class Main {

    public static void main(String[] args) {
        BSTImpl<Integer> b = new BSTImpl<>();

        b.insert(6);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(6));
        System.out.println(b.sucessor(6));
        System.out.println(b.minimum());

        System.out.println("---------------------------");
        b.insert(7);

        System.out.println(b.maximum());
        System.out.println(b.predecessor(7));
        System.out.println(b.sucessor(7));
        System.out.println(b.minimum());


        System.out.println("---------------------------");

        b.insert(-6);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(-1));
        System.out.println(b.sucessor(-1));
        System.out.println(b.minimum());

        System.out.println("---------------------------");

        b.insert(0);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(0));
        System.out.println(b.sucessor(0));
        System.out.println(b.minimum());


        System.out.println("---------------------------");

        b.insert(-2);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(-2));
        System.out.println(b.sucessor(-2));
        System.out.println(b.minimum());

        System.out.println("---------------------------");

        b.insert(3);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(3));
        System.out.println(b.sucessor(3));
        System.out.println(b.minimum());

        System.out.println("-------_" + b.getRoot().getData());

        System.out.println("---------------------------");

        b.insert(8);
        System.out.println(b.maximum());
        System.out.println(b.predecessor(8));
        System.out.println(b.sucessor(8));
        System.out.println(b.minimum());

        System.out.println(b.size());
    }
}
