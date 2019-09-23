package adt.linkedList;

import java.util.ArrayList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		if (head.isNIL()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = this.head;

		while(!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxNode = this.head;

		while(!auxNode.isNIL() && !auxNode.data.equals(element)) {
			auxNode = auxNode.next;
		}

		T resultado;
		if (!auxNode.isNIL()) {
			resultado = auxNode.data;
		} else {
			resultado = null;
		}
		return resultado;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = this.head;

		if (this.head.isNIL()) {
			this.head = new SingleLinkedListNode<T>(element, this.head);

		} else {

			while(!auxHead.next.isNIL()) {
				auxHead = auxHead.next;
			}

			auxHead.next = new SingleLinkedListNode<T>(element, auxHead.next);
		}
	}

	@Override
	public void remove(T element) {
		if (this.head.data.equals(element)) {
			this.head = this.head.next;
		} else {
			SingleLinkedListNode<T> aux = this.head;
			SingleLinkedListNode<T> previous = this.head;

			while(!aux.isNIL() && !aux.data.equals(element)) {
				previous = aux;
				aux = aux.getNext();
			}

			if (!aux.isNIL()) {
				previous.next = aux.next;
			}



		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> arraySaida = new ArrayList<>();
		SingleLinkedListNode<T> aux = this.head;

		while(!aux.isNIL()) {
			arraySaida.add(aux.getData());
			aux = aux.getNext();
		}


		return (T[]) arraySaida.toArray();


	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
