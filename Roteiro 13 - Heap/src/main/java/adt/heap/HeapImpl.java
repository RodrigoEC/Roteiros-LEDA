package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita 
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	public void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int maior = position;

		if (left <= this.index && comparator.compare(this.heap[left], this.heap[maior]) > 0)  {
			maior = left;
		}
		if (right <= this.index && comparator.compare(this.heap[right], this.heap[maior]) > 0) {
			maior = right;
		}

		if (maior != position) {
			Util.swap(this.heap, maior, position);
			heapify(maior);
		}

	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if (element != null) {
			int apont = ++this.index;

			while (apont > 0 && this.comparator.compare(this.heap[parent(apont)], element) < 0) {
				this.heap[apont] =  this.heap[parent(apont)];
				apont = parent(apont);
			}

			this.heap[apont] = element;

		}



	}

	@Override
	public void buildHeap(T[] array) {
		this.heap = array;
		this.index = array.length - 1;

		for (int i = size() / 2; i > -1; i--) {
			heapify(i);
		}
	}

	@Override
	public T extractRootElement() {
		T resultado = null;
		if (size() != 0) {
			resultado = this.heap[0];
			this.heap[0] = this.heap[this.index--];

			heapify(0);

		}
		return resultado;
	}

	@Override
	public T rootElement() {
		T resultado = null;
		if (size() != 0) {
			resultado = this.heap[0];
		}

		return resultado;
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array.length != 0) {
			buildHeap(array);

			for (int i = array.length - 1; i >=  1; i--) {
				this.index--;
				Util.swap(array, i, 0);
				heapify(0);
			}
			this.index--;

		}

		return array;
	}

	public T[] merge(T[] array1, T[] array2) {
		this.heap = (T[]) new Comparable[INITIAL_SIZE];
		ArrayList<T> lista = new ArrayList<>();

		int contador = 0;
		while (contador < (array1.length + array2.length)) {
			if (contador < array1.length) {
				if (!lista.contains(array1[contador])) {
					lista.add(array1[contador]);
				}
			} else {
				if (!lista.contains(array2[contador - array1.length])) {
					lista.add(array2[contador - array1.length]);
				}
			}

			contador++;
		}

		return lista.toArray((T[]) new Comparable[lista.size()]);
	}

	public T[] elementsByLevel(int level) {
		return elementsByLevel(level, this.rootElement());
	}

	private T[] elementsByLevel(int level, T elem) {
		ArrayList<T> lista = new ArrayList<>();
		double elemNivelAcima = Math.pow(2, level) - 1;
		double elemNivelAtual = Math.pow(2, level + 1) - 1;

		while (elemNivelAcima < elemNivelAtual && elemNivelAcima < this.heap.length) {
			lista.add(this.heap[(int) elemNivelAcima++]);
		}

		return lista.toArray((T[]) new Comparable[lista.size()]);




	}


	@Override
	public int size() {
		return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
