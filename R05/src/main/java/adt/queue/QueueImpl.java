package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T resultado;
		if (tail == - 1) {
			resultado =  null;
		} else {
			resultado = array[0];
		}
		return resultado;
	}

	@Override
	public boolean isEmpty() {
		boolean resultado;
		if (this.tail == -1) {
			resultado = true;
		} else {
			resultado = false;
		}

		return  resultado;
	}

	@Override
	public boolean isFull() {
		boolean resultado;
		if (this.tail == this.array.length - 1) {
			resultado = true;
		} else {
			resultado = false;
		}
		 return resultado;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (!isFull()) {
				array[++tail] = element;
			} else {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T resultado;
		if(!isEmpty()) {
			resultado = array[0];
			shiftLeft();
			tail--;
		} else {
			throw new QueueUnderflowException();
		}
		return resultado;
	}

}
