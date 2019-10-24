package adt.bst.extended;

import java.util.ArrayList;
import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado.
 *
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		this.root = new BSTNode<>();
		for (T elem : array) {
			this.insert(elem);
		}

		return order();
	}

	@Override
	public T[] reverseOrder() {
		T[] arraySaida = (T[]) new Comparable[this.size()];
		ArrayList<T> lista =  new ArrayList<>();

		if (!this.isEmpty()) {
			reverseOrder(this.root, lista);
		}

		return lista.toArray(arraySaida);
	}

	private void reverseOrder(BSTNode<T> node, ArrayList<T> lista) {
		if (!node.isEmpty()) {
			reverseOrder(getRight(node), lista);
			lista.add(node.getData());
			reverseOrder(getLeft(node), lista);
		}


	}


	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> resultado = new BSTNode<>();
		if (element != null) {
			BSTNode<T> elemAtual = this.root;

			while (!elemAtual.isEmpty() && resultado.isEmpty()) {
				if (element.equals(elemAtual.getData())) {
					resultado = elemAtual;
				} else if (comparator.compare(element, elemAtual.getData()) > 0) {
					elemAtual = (BSTNode<T>) elemAtual.getRight();

				} else if (comparator.compare(element, elemAtual.getData()) < 0) {
					elemAtual = (BSTNode<T>) elemAtual.getLeft();
				}
			}
		}

		return resultado;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}
	}

	@Override
	protected void insert(BSTNode<T> node, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);

		} else {
			if (comparator.compare(element, node.getData()) < 0) {
				insert(getLeft(node), element);
			} else if (comparator.compare(element, node.getData()) > 0) {
				insert(getRight(node), element);
			}
		}
	}

	@Override
	protected boolean menorQue(BSTNode<T> node1, BSTNode<T> node2) {
		return (comparator.compare(node1.getData(), node2.getData()) < 0);
	}



	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
