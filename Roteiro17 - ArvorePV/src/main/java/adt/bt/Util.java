package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> result = null;


		if (node != null && !node.isEmpty()) {
			BSTNode<T> pivot =  (BSTNode<T>) node.getRight();
			node.setRight(pivot.getLeft());
			node.getRight().setParent(node);

			pivot.setParent(node.getParent());
			pivot.setLeft(node);
			node.setParent(pivot);
			if (pivot.getParent() != null) {
				if (pivot.getParent().getData().compareTo(pivot.getData()) > 0) {
					pivot.getParent().setLeft(pivot);
				} else {
					pivot.getParent().setRight(pivot);
				}
			}
			result = pivot;
		}

		return result;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> result = null;


		if (node != null && !node.isEmpty()) {
			BSTNode<T> pivot = (BSTNode<T>) node.getLeft();

			node.setLeft(pivot.getRight());
			node.getLeft().setParent(node);

			pivot.setParent(node.getParent());
			if (pivot.getParent() != null) {
				if (pivot.getParent().getData().compareTo(pivot.getData()) > 0) {
					pivot.getParent().setLeft(pivot);
				} else {
					pivot.getParent().setRight(pivot);
				}
			}
			pivot.setRight(node);
			node.setParent(pivot);

			result = pivot;
		}

		return  result;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
