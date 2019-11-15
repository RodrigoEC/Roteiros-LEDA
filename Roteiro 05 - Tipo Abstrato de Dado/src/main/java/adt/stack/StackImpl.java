package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if (size > 0) {
			array = (T[]) new Object[size];
			top = -1;
		}

	}

	@Override
	public T top() {
		T resultado;
		if (this.top == -1) {
			resultado = null;
		} else {
			resultado = array[top];
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		boolean resultado;
		if (this.top == -1) {
			resultado = true;
		} else {
			resultado = false;
		}

		return resultado;
	}

	@Override
	public boolean isFull() {
		boolean resultado;
		if (this.top == this.array.length - 1) {
			resultado = true;
		} else {
			resultado = false;
		}

		return resultado;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (this.top + 1 == this.array.length) {
			throw new StackOverflowException();
		}

		this.top++;
		this.array[top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (this.top == -1) {
			throw new StackUnderflowException();
		}
		top--;

		return array[top + 1];
	}

}
