package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;
	protected int tamanhoAtual;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
		this.tamanhoAtual = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (this.tamanhoAtual == this.size) {
				throw new StackOverflowException();
			} else {
				this.top.insert(element);
				tamanhoAtual++;
			}
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (tamanhoAtual == 0) {
			throw new StackUnderflowException();
		} else {
			T elemento = this.top();
			this.top.removeLast();
			tamanhoAtual--;
			return elemento;
		}
	}

	@Override
	public T top() {
		if (tamanhoAtual == 0) {
			return null;
		} else {
			return ((DoubleLinkedListImpl<T>)this.top).getLast().getData();
		}
	}

	@Override
	public boolean isEmpty() {
		if (this.top.isEmpty()) {
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
