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
		}

		rebalance(node);
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

		if (element != null && !node.isEmpty()) {
			if (node.isLeaf()) {

				node.setData(null);
				node.setLeft(null);
				node.setRight(null);

				if (getParent(node) != null) {
					if (!getParent(node).getLeft().isEmpty()) {
						rebalanceUp(getLeft(getParent(node)));
					} else if (!getParent(node.getRight()).isEmpty()) {
						rebalanceUp(getRight(getParent(node)));
					}
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
		int r =(height(getLeft(node)) - height(getRight(node)));
		return r;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (calculateBalance(node) >= 1 && calculateBalance(getLeft(node)) >= 0) {
				Util.rightRotation(node);
			} else if (calculateBalance(node) <= -1 && calculateBalance(getRight(node)) <= 0) {
				Util.leftRotation(node);
			} else if (calculateBalance(node) >= 1) {
				Util.leftRotation(getLeft(node));
				Util.rightRotation(node);
			} else {
				Util.rightRotation(getRight(node));
				Util.leftRotation(node);
			}
		}
	}



	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> parent = getParent(node);

		while(parent != null) {
			rebalance(parent);
			parent = getParent(parent);
		}
	}
}
