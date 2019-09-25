package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;
	protected int tamanhoAtual;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
		this.tamanhoAtual = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (this.tamanhoAtual == this.size) {
				throw new QueueOverflowException();
			} else {
				this.list.insert(element);
				this.tamanhoAtual++;
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (this.tamanhoAtual == 0) {
			throw new QueueUnderflowException();
		} else {
			T elemento = this.head();
			this.list.removeFirst();
			return elemento;
		}
	}

	@Override
	public T head() {
		T head = null;
		if (!this.list.isEmpty()) {
			head = ((SingleLinkedListImpl<T>) this.list).getHead().getData();
		}
		return head;
}

	@Override
	public boolean isEmpty() {
		if(this.list.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isFull() {
		if (this.tamanhoAtual == this.size) {
			return true;
		} else {
			return false;
		}
	}

}
