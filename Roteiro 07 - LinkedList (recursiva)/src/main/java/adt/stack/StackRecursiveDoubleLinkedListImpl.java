package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;
	protected int tamanhoAtual;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
		this.tamanhoAtual = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.tamanhoAtual == this.size) {
			throw new StackOverflowException();
		} else {
			this.top.insertFirst(element);
			this.tamanhoAtual++;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T result;
		if (this.tamanhoAtual == 0) {
			throw new StackUnderflowException();
		} else {
			result = top();
			this.top.removeFirst();
			this.tamanhoAtual--;
		}
		return result;
	}

	@Override
	public T top() {
		T result = null;
		if (!this.top.isEmpty()) {
			result = ((RecursiveDoubleLinkedListImpl<T>) this.top).getData();

		}
		return result;

	}

	@Override
	public boolean isEmpty() {
		return (this.tamanhoAtual == 0);
	}

	@Override
	public boolean isFull() {
		return (this.tamanhoAtual == this.size);
	}

}
