package adt.btree;

import java.util.ArrayList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root, -1);
	}

	private int height(BNode<T> node, int contador) {
		if (!node.isLeaf()) {
			contador = height(node.getChildren().getFirst(), ++contador);
		}
		return contador;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {
		ArrayList<BNode<T>> lista = new ArrayList<>();
		depthLeftOrder(this.root, lista);
		BNode<T>[] arraySaida = (BNode<T>[]) new BNode[lista.size()];
		return lista.toArray(arraySaida);
	}

	private void depthLeftOrder(BNode<T> node, ArrayList<BNode<T>> lista) {

		if (!node.isEmpty()) {
			lista.add(node);

			for (int i = 0; i < node.getChildren().size(); i++) {
				depthLeftOrder(node.getChildren().get(i), lista);
			}
		}
	}

	@Override
	public int size() {
		return (int) Math.pow(2, height() + 1) - 1;
	}

	@Override
	public BNodePosition<T> search(T element) {
		BNodePosition<T> resultado = new BNodePosition<T>();

		if (element != null) {
			resultado = search(element, this.root);
		}

		return resultado;

	}

	private BNodePosition<T> search(T element, BNode<T> node) {
		BNodePosition<T> nodeSaida;

		int contador = 0;
		while (contador <= node.size() && element.compareTo(node.getElementAt(contador)) > 0) {
			contador++;
		}

		if (contador <= node.size() && element.equals(node.getElementAt(contador))) {
			nodeSaida = new BNodePosition<>(node, contador);

		} else if (node.isLeaf()) {
			nodeSaida = new BNodePosition<>();

		} else {
			nodeSaida = search(element, node.getChildren().get(contador));
		}

		return nodeSaida;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(this.root, element);
		}

	}

	private void insert(BNode<T> node, T element) {





	}

	private void split(BNode<T> node) {
		if (node.)
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}
