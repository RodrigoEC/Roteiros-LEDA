import adt.rbtree.RBNode;
import adt.rbtree.RBTreeImpl;

public class Main {
    public static void main(String[] args) {
        RBTreeImpl<Integer> myRB = new RBTreeImpl<>();
        myRB.insert(11);
        myRB.insert(2);
        myRB.insert(14);
        myRB.insert(1);

        System.out.println(myRB.verifyChildrenOfRedNodes((RBNode<Integer>) myRB.getRoot()));
    }
}
