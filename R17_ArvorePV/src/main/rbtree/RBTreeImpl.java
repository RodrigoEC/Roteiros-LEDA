package main.rbtree;

import main.bst.BSTImpl;
import main.bst.BSTNode;
import main.bt.Util;
import main.rbtree.RBNode.Colour;

import java.util.ArrayList;
import java.util.List;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return 0;
	}


	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
		// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodesRecursive((RBNode<T>) this.root);
	}

	private boolean verifyChildrenOfRedNodesRecursive(RBNode<T> node) {
		return true;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return true;
	}


	@Override
	public void insert(T element) {
		if (element != null) {
			insert((RBNode<T>) this.root, element);
		}
	}

	private void insert (RBNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<>());
			node.setRight(new RBNode<>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			node.setColour(Colour.RED);
			fixUpCase1(node);
		} else {
			if (node.getData().compareTo(element) > 0) {
				insert((RBNode<T>) node.getLeft(), element);
			} else {
				insert((RBNode<T>) node.getRight(), element);
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> lista = new ArrayList<>();

		preOrder((RBNode<T>) this.root, lista);
		RBNode<T>[] array = new RBNode[size()];

		return lista.toArray(array);
	}

	protected void preOrder(RBNode<T> node, ArrayList<RBNode<T>> list) {
        if (!node.isEmpty()) {

            list.add(node);
            preOrder((RBNode<T>) node.getLeft(),  list);
            preOrder((RBNode<T>) node.getRight(), list);
        }
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.getParent() == null) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}

	}

	protected void fixUpCase2(RBNode<T> node) {
		if (!theParent(node).getColour().equals(Colour.BLACK)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		if (theUncle(node).getColour().equals(Colour.RED)) {
			theParent(node).setColour(Colour.BLACK);
			theUncle(node).setColour(Colour.BLACK);
			theParent(theParent(node)).setColour(Colour.RED);
			fixUpCase1(theParent(theParent(node)));
		} else {
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		if (!isLeftChild(node) && isLeftChild(theParent(node))) {
			Util.leftRotation(theParent(node));
			next = (RBNode<T>) node.getLeft();
		} else if (isLeftChild(node) && !isLeftChild(theParent(node))) {
			Util.rightRotation(theParent(node));
			next = (RBNode<T>) node.getRight();
		}

		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		theParent(node).setColour(Colour.BLACK);
		theParent(theParent(node)).setColour(Colour.RED);

		if (isLeftChild(node)) {
			Util.rightRotation(theParent(theParent(node)));
		} else {
			Util.leftRotation(theParent(theParent(node)));
		}

	}




	private RBNode<T> theParent(RBNode node) {
		return (RBNode<T>) node.getParent();
	}

	private RBNode<T> theUncle(RBNode node) {
		RBNode<T> resultado;
		if (node.getParent().getLeft().equals(node)) {
			resultado = (RBNode<T>) node.getParent().getRight();
		} else {
			resultado = (RBNode<T>) node.getParent().getLeft();

		}
		return resultado;
	}

	private boolean isLeftChild(RBNode<T> node) {
		return (node.getParent().getLeft().equals(node));
	}
}
