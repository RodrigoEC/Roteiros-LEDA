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
		if (node != null && !node.isEmpty()) {
			if (!node.isLeaf()) {
				contador = height(node.getChildren().getFirst(), ++contador);
			}else {
				contador++;
			}
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
		BNodePosition<T> nodeSaida = new BNodePosition<>();

		if (element != null) {

			int contador = 0;
			while (contador < node.size() && element.compareTo(node.getElementAt(contador)) > 0) {
				contador++;
			}

			if (contador < node.size() && element.equals(node.getElementAt(contador))) {
				nodeSaida = new BNodePosition<>(node, contador);

			} else if (node.isLeaf()) {
				nodeSaida = new BNodePosition<>();

			} else {
				nodeSaida = search(element, node.getChildren().get(contador));
			}

		}
		return nodeSaida;

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.root);
		}

	}

	private void insert(T element, BNode<T> node) {

		if (element != null && !node.equals(search(element))) {
			int contador = 0;
			while (contador < node.size() && element.compareTo(node.getElementAt(contador)) > 0) {
				contador++;
			}

			if (contador >= node.getElements().size() || !node.getElementAt(contador).equals(element)) {
				if (node.isLeaf()) {
					node.addElement(element);
				} else {
					this.insert(element, node.getChildren().get(contador));
				}
			}

			if (node.getMaxKeys() < node.size()) {
				this.split(node);

			}
		}
	}

	private void split(BNode<T> node) {
		BNode<T> auxNode = 	node;
		int numChaves = node.elements.size();
		int mediumIndex = numChaves / 2;
		T mediumElement = node.getElementAt(mediumIndex);

		BNode<T> leftNode = constructNode(node, 0, mediumIndex);
		BNode<T> rightNode = constructNode(node, mediumIndex + 1, numChaves);

		if (node.getParent() == null) {
			BNode<T> newRoot = new BNode<>(this.order);
			newRoot.addElement(mediumElement);
			auxNode.setParent(newRoot);
			this.root = newRoot;

			auxNode = this.root;
			auxNode.addChild(0, leftNode);
			auxNode.addChild(1, rightNode);
			auxNode.getChildren().get(0).setParent(newRoot);
			auxNode.getChildren().get(1).setParent(newRoot);

		} else {
			BNode<T> dad = node.getParent();
			dad.addElement(mediumElement);
			dad.removeChild(auxNode);
			dad.addChild(0, leftNode);
			dad.addChild(1, rightNode);

			if (this.order < dad.getElements().size()) {
				split(dad);
			}
		}

	}


	private BNode<T> constructNode(BNode<T> node, int inicio, int fim) {
		BNode<T> nodeSaida = new BNode<>(this.order);
		for (int i = inicio; i < fim; i++) {
			nodeSaida.addElement(node.getElementAt(i));
		}
		if (node.getChildren().size() > 0) {
			int contador = 0;
			for(int i = inicio; i < fim; i++) {
				BNode<T> n = node.getChildren().get(i);
				nodeSaida.addChild(contador++, n);
			}
		}
		return nodeSaida;
	}

	private void promote(BNode<T> node) {
		// Sinceramente não tenho a menor ideia do que eh isso...não lembro de adalberto falando sobre e não tem na
		// interface BTree, então..
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
