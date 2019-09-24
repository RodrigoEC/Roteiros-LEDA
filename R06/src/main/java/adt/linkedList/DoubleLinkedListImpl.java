package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;


	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<T>();
			DoubleLinkedListNode<T> elem = new DoubleLinkedListNode<T>(element, nil, last);

			if (isEmpty()) {
				super.head = this.last = elem;
			} else {
				this.last.next = elem;
				this.last = elem;
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> previous = new DoubleLinkedListNode<T>();
		DoubleLinkedListNode<T> next = (DoubleLinkedListNode<T>)this.getHead();

		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, next, previous);

		((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<>();

		if (this.head.isNIL()) {
			this.last = newHead;
		}

		this.head = newHead;
	}

	@Override
	public void removeFirst() {
		if (!this.head.isNIL()) {
			this.head = head.next;
		} else {
			this.last = (DoubleLinkedListNode<T>) this.head;
		}

		((DoubleLinkedListNode<T>) this.head).previous = new DoubleLinkedListNode<T>();
	}

	@Override
	public void removeLast() {
		if (!this.getLast().isNIL()) {
			this.setLast(last.getPrevious());

			if (this.last.isNIL()) {
				this.setHead(this.last);
			}
			this.last.next = new DoubleLinkedListNode<>();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
	public DoubleLinkedListNode<T> getHead() {
		return (DoubleLinkedListNode<T>)this.head;
	}
}
