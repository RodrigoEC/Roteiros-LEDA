package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		if (size > 0) {
			array = (T[]) new Object[size];
			head = -1;
			tail = -1;
			elements = 0;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (this.elements < this.array.length) {
				this.elements++;

				if (this.tail == array.length - 1) {
					this.tail = -1;
				}
				this.array[++this.tail] = element;

			} else {
				throw new QueueOverflowException();
			}
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T resultado;
		if (this.elements > 0) {
			this.elements--;

			head++;
			if (this.head == array.length) {
				this.head = 0;
			}

			resultado = array[head];

		} else {
			throw new QueueUnderflowException();
		}

		return  resultado;
	}

	@Override
	public T head() {
		T resultado;
		if (elements == 0) {
			resultado =  null;
		} else {
			resultado = array[head + 1];
		}
		return resultado;
	}


	@Override
	public boolean isEmpty() {
		boolean resultado;
		if (elements == 0) {
			resultado =  true;
		} else {
			resultado = false;
		}
		return resultado;
	}

	@Override
	public boolean isFull() {
		boolean resultado;
		if (this.elements == array.length) {
			resultado = true;
		} else {
			resultado = false;
		}
		return  resultado;
	}

}
