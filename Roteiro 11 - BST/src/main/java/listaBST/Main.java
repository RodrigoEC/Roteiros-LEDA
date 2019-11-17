package listaBST;

import adt.bst.BSTImpl;

public class Main {

    public static void main(String[] args) {
        BSTImpl<Integer> tree = new BSTImpl<>();
        Integer[] array = { 6, 23, -34, 5, 9, 2, 0, 76, 12, 67, 232, -40, 3 };
        for (int i : array) {
            tree.insert(i);
        }
        ListaBST b = new ListaBST();

        System.out.println(b.contaFolhas(tree));

    }
}
