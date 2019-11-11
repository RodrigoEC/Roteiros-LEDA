package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < this.maxHeight; i++) {
			this.root.forward[i] = this.NIL;
		}

	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		SkipListNode<T>[] update = new SkipListNode[this.maxHeight];
		SkipListNode<T> auxNode = this.root;
		for (int i  = this.maxHeight - 1; i >= 0; i--) {
			while(auxNode.forward[i].getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
			update[i] = auxNode;
		}
		auxNode = auxNode.getForward(0);
		if (!newValue.equals(auxNode.getValue())) {
			auxNode = new SkipListNode(key, height, newValue);

			for(int i = 0; i < height; i++) {
				auxNode.forward[i] = update[i].forward[i];
				update[i].forward[i] = auxNode;
			}

		}

		
	}

	@Override
	public void remove(int key) {
		

		
	}

	@Override
	public int height() {
		return 0;
	}

	@Override
	public SkipListNode<T> search(int key) {
		SkipListNode<T> auxNode = this.root;

		for (int i  = this.maxHeight - 1; i >= 0; i--) {
			while(auxNode.forward[i].getKey() < key) {
				auxNode = auxNode.getForward(i);
			}
		}
		auxNode = auxNode.getForward(0);

		SkipListNode<T> resultado;
		if (auxNode.getKey() == key) {
			resultado = auxNode;
		} else {
			resultado = null;
		}

		return resultado;
	}

	@Override
	public int size() {
		int contador = 0;
		SkipListNode<T> auxNode = this.root.getForward(0);

		while(!auxNode.equals(NIL)) {
			contador++;
			auxNode = auxNode.getForward(0);
		}

		return contador;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T>[] arraySaida = new SkipListNode[this.size() + 2];
		SkipListNode<T> auxNode = this.root;
		for(int i = 0; i < this.size() + 2; i++) {
			arraySaida[i] = auxNode;
			auxNode = auxNode.getForward(0);
		}

		return arraySaida;
	}

}
