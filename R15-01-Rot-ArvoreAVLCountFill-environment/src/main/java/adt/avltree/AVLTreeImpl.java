package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		} else {
			if (element.compareTo(node.getData()) < 0) {
				insert(getLeft(node), element);
			} else if (element.compareTo(node.getData()) > 0) {
				insert(getRight(node), element);
			}
			rebalanceUp(node);

		}


	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (element != null && !node.isEmpty()) {
			if (node.isLeaf()) {

				node.setData(null);
				node.setLeft(null);
				node.setRight(null);

				if (!this.root.isEmpty()) {
					rebalanceUp(node);

				}
			} else if (hasOneChild(node)) {
				removeOneChild(node);
			} else {
				removeTwoChildren(node);
			}
		}
	}

	protected void removeOneChild(BSTNode<T> node) {
		if (node.getParent() != null) {
			if (!getParent(node).getLeft().equals(node)) {
				if (!getLeft(node).isEmpty()) {
					getParent(node).setRight(node.getLeft());
					node.getLeft().setParent(node.getParent());

				} else {
					getParent(node).setRight(node.getRight());
					node.getRight().setParent(node.getParent());
				}
			} else {
				if (!getLeft(node).isEmpty()) {
					getParent(node).setLeft(node.getLeft());
					node.getLeft().setParent(node.getParent());
				} else {
					getParent(node).setLeft(node.getRight());
					node.getRight().setParent(node.getParent());
				}
			}
		} else {
			if (!getLeft(node).isEmpty()) {
				this.root = getLeft(this.root);

			} else if (getRight(node) != null) {
				this.root = getRight(this.root);
			}
			this.root.setParent(null);
		}

		rebalanceUp(node);
	}

	protected void removeTwoChildren(BSTNode<T> node) {
		BSTNode<T> sucessor = sucessor(node.getData());
		T suc = sucessor.getData();
		remove(sucessor.getData());
		node.setData(suc);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int r = (height(getLeft(node)) - height(getRight(node)));
		return r;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (calculateBalance(node) > 1 && calculateBalance(getLeft(node)) >= 0) { //LL
				rightRotation(node);
			} else if (calculateBalance(node) < -1 && calculateBalance(getRight(node)) <= 0) { //RR
				leftRotation(node);
			} else if (calculateBalance(node) > 1 && calculateBalance(getLeft(node)) < 0) { //LR
				leftRotation(getLeft(node));
				rightRotation(node);
			} else if (calculateBalance(node) < -1 && calculateBalance(getRight(node)) > 0) { //RL
				rightRotation(getRight(node));
				leftRotation(node);
			}
		}
	}


	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> parent = getParent(node);

			while (parent != null) {
				rebalance(parent);
				parent = getParent(parent);
			}
		}

	}

	protected void leftRotation(BSTNode<T> node) {
		if (node == null) {
			return;
		}
		BSTNode<T> aux = Util.leftRotation(node);
		if (this.root.equals(node)) {
			this.root = aux;
		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {
		if (node != null) {
			BSTNode<T> aux = Util.rightRotation(node);
			if (this.root.equals(node)) {
				this.root = aux;
			}

		}
	}
}