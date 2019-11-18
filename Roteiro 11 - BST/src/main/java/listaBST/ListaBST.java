package listaBST;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class ListaBST {

    public int contaFolhas(BSTImpl<Integer> tree) {
        BSTNode<Integer> auxNode = tree.getRoot();
        int contador = 0;
        contador += contaFolhas(contador, auxNode);
        return contador;
    }

    private int contaFolhas(int contador, BSTNode<Integer> auxNode) {

        if (auxNode != null) {
            if (auxNode.isLeaf()) {
                contador++;
            } else {
                if (!auxNode.getLeft().isEmpty()) {
                    contador = contaFolhas(contador, (BSTNode<Integer>) auxNode.getLeft());
                }
                if (!auxNode.getRight().isEmpty()) {
                    contador = contaFolhas(contador, (BSTNode<Integer>) auxNode.getRight());
                }
            }
        }
        return contador;
    }

    // Questão 1 da lista mais complexa de BST
    private BSTNode<Integer> minimum(BSTImpl<Integer> tree) {
        return minimum(tree.getRoot());
    }

    private BSTNode<Integer> minimum(BSTNode<Integer> node) {
        BSTNode<Integer> resultado = null;
        if (node.isEmpty()) {
            resultado = (BSTNode<Integer>) node.getParent();
        } else {
            minimum((BSTNode<Integer>) node.getLeft());
        }
        return resultado;
    }


    // Questão 2 da lista mais complexa de BST

    public boolean isDecendent(Integer d, Integer p) {
        boolean isDecendent = true;

        return isDecendent;
    }
}
