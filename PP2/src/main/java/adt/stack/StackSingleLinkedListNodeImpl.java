package adt.stack;

import adt.linkedList.SingleLinkedListNode;

/**
 * Classe que representa um apilha implementada usando-se um noh de uma lista
 * simplesmente ligada, como estrutura sobrejacente. 
 * 
 * Restricoes:
 * - voce DEVE obedecer a politica de acesso e complexidade dos metodos de pilha, ou seja, todos em O(1).
 * - voce NÃO pode usar memoria extra (estrutura auxiliar)
 * - qualquer metodo auxiliar que voce necessite DEVE ser implementado nesta classe
 * - voce NÃO pode modificar a classe SingleLinkedListNode
 * 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class StackSingleLinkedListNodeImpl<T> implements Stack<T> {
	
	private SingleLinkedListNode<T> top;
	private int tamanhoMax;
	private int tamanhoAtual;

	/**
	 * A pilha para ser criada precisa ter um tamanho maximo
	 * @param tamanhoMaximo tamanho maximo do stack
	 */
	public StackSingleLinkedListNodeImpl(int tamanhoMaximo) {
		this.top = new SingleLinkedListNode<T>();

		if (tamanhoMaximo < 0) {
			this.tamanhoMax = 0;
		} else {
			this.tamanhoMax = tamanhoMaximo;
		}
		this.tamanhoAtual = 0;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {

			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<>(element, this.top);
			this.top = newHead;
			this.tamanhoAtual++;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}

		T topData = this.top.getData();

		this.top = this.top.getNext();
		this.tamanhoAtual--;

		return topData;
	}

	@Override
	public T top() {
		return this.top.getData();
	}

	@Override
	public boolean isEmpty() {
		return (this.tamanhoAtual == 0);
	}

	@Override
	public boolean isFull() {
		return (this.tamanhoAtual == this.tamanhoMax);
	}

}
