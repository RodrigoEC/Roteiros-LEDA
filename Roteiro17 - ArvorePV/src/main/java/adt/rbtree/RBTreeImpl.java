package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;

import static java.awt.Color.BLACK;
import static java.awt.Color.black;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	public int blackHeight() {
		int resultado = -1;
		if (!this.root.isEmpty()) {
			resultado = blackHeight((RBNode<T>) this.root);
		}
		return resultado;
	}

	private int blackHeight(RBNode<T> node) {
		int resultado = 0;
		if (node != null && !node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				resultado--;
			}
			resultado += 1 + (Math.max(blackHeight(getLeft(node)), blackHeight(getRight(node))));
		}
		return resultado;
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
		return verifyChildrenOfRedNodes((RBNode<T>) this.root);
	}

	public boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean resultado = true;

		if (node != null && !node.isEmpty()) {
			if(node.getColour().equals(Colour.BLACK)) {
				resultado = verifyChildrenOfRedNodes(getLeft(node));
				if (resultado) {
					resultado = verifyChildrenOfRedNodes(getRight(node));
				}
			} else {
				if (getRight(node).getColour().equals(Colour.RED) || getLeft(node).getColour().equals(Colour.RED)) {
					resultado = false;
				} else {
					if (getLeft(node).getColour().equals(Colour.BLACK)) {
						resultado = verifyChildrenOfRedNodes(getLeft(node));
					}
					if (getRight(node).getColour().equals(Colour.BLACK)) {
						resultado = verifyChildrenOfRedNodes(getRight(node));
					}
				}
			}

		}

		return resultado;
	}

	/**
	 * Verifies the black-height property from the root.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) this.root);
	}

	private boolean verifyBlackHeight(RBNode<T> node) {
		boolean resultado = node.isEmpty() || blackHeight(getLeft(node)) == blackHeight(getRight(node));

		if (resultado && !node.isEmpty()) {
			resultado = verifyBlackHeight(getLeft(node)) && verifyBlackHeight(getRight(node));
		}

		return resultado;
	}


	@Override
	public void insert(T element) {
		if (element != null) {
			RBNode<T> node = insert((RBNode<T>) this.root, element);
			fixUpCase1(node);
		}
	}

	private RBNode<T> insert(RBNode<T> node, T element) {
		RBNode<T> resultado = null;
		if (node.isEmpty()) {
			node.setData(element);
			node.setColour(Colour.RED);
			node.setLeft(new RBNode<>());
			node.setRight(new RBNode<>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
			resultado = node;
		} else {
			if (node.getData().compareTo(element) > 0) {
				resultado = insert((RBNode<T>) node.getLeft(), element);

			} else if (node.getData().compareTo(element) < 0) {
				resultado = insert((RBNode<T>) node.getRight(), element);
			}
		}

		return resultado;

	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> lista = new ArrayList<>();
		preOrder((RBNode<T>) this.root, lista);
		RBNode<T>[] array = new RBNode[size()];
		return lista.toArray(array);
	}

	protected void preOrder(RBNode<T> node, ArrayList<RBNode<T>> lista) {
		if (!node.isEmpty()) {

			lista.add(node);
			preOrder((RBNode<T>) node.getLeft(), lista);
			preOrder((RBNode<T>) node.getRight(), lista);
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
			RBNode<T> grandPa = theParent(theParent(node));

			grandPa.setColour(Colour.RED);
			theParent(node).setColour(Colour.BLACK);
			theUncle(node).setColour(Colour.BLACK);

			fixUpCase1(grandPa);
		} else {
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;

		if (!isLeftChild(node) && isLeftChild(theParent(node))) {
			leftRotation(theParent(node));
			next = (RBNode<T>) node.getLeft();

		} else if (isLeftChild(node) && !isLeftChild(theParent(node))) {
			rightRotation(theParent(node));
			next = (RBNode<T>) node.getRight();
		}

		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> grandPa = theParent(theParent(node));

		theParent(node).setColour(Colour.BLACK);
		grandPa.setColour(Colour.RED);
		if (isLeftChild(node)) {
			rightRotation(grandPa);

		} else {
			leftRotation(grandPa);
		}



	}


	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			this.root = newNode;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			this.root = newNode;
		}
	}
	private RBNode<T> theParent(RBNode node) {
		return (RBNode<T>) node.getParent();
	}

	private RBNode<T> theUncle(RBNode node) {
		RBNode<T> grandPa = (RBNode<T>) node.getParent().getParent();

		RBNode<T> resultado;
		if (grandPa.getLeft().equals(node.getParent())) {
			resultado = (RBNode<T>) grandPa.getRight();
		} else {
			resultado = (RBNode<T>) grandPa.getLeft();

		}
		return resultado;
	}

	private RBNode<T> getRight(BTNode<T> node) {
		return (RBNode<T>) node.getRight();
	}

	private RBNode<T> getLeft(BTNode<T> node) {
		return (RBNode<T>) node.getLeft();
	}

	private boolean isLeftChild(RBNode<T> node) {
		return (node.getParent().getLeft().equals(node));
	}
}