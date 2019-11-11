import adt.avltree.AVLCountAndFillImpl;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] keys = { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15, 20, 21};
        AVLCountAndFillImpl<Integer> tree1 = new AVLCountAndFillImpl<Integer>();

        tree1.fillWithoutRebalance(keys);
        System.out.println(tree1.LLcount());
        System.out.println(tree1.LRcount());
        System.out.println(tree1.RLcount());
        System.out.println(tree1.RRcount());
        System.out.println(Arrays.toString(tree1.preOrder()));





    }
}
