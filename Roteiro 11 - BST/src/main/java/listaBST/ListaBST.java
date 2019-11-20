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

    public boolean isDecendent(Integer d, Integer p, BSTImpl<Integer> tree) {
        BSTNode<Integer> nodeD = tree.search(d);
        BSTNode<Integer> nodeP = tree.search(p);
        boolean isDecendent = false;


        if (!nodeD.isEmpty() && !nodeP.isEmpty()) {

            BSTNode<Integer> dad = (BSTNode<Integer>) nodeD.getParent();
            if (nodeD.getParent() != null && dad.equals(nodeP)) {
                isDecendent = true;
            } else if (nodeD.getParent() != null) {
                isDecendent = isDecendent(dad.getData(), p, tree);
            }
        }


        return isDecendent;
    }


    // QUESTÃO 03
    public int distance(Integer x1, Integer x2, BSTImpl<Integer> tree) {
        int dist = 0;
        if (isDecendent(x1, x2, tree) || x1.equals(x2)) {
            BSTNode<Integer> nodeX1 = tree.search(x1);
            BSTNode<Integer> nodeX2 = tree.search(x2);
            BSTNode<Integer> dad = (BSTNode<Integer>) nodeX1.getParent();

            if (!nodeX1.equals(nodeX2)) {
                dist =  1 + distance(dad.getData(), x2, tree);
            }
        } else {
            throw new IllegalArgumentException("is not decendent");
        }

        return dist;
    }
}
