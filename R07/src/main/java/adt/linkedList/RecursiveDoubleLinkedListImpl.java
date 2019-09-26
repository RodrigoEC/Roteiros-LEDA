package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
		previous = new RecursiveDoubleLinkedListImpl<>(null, this, null);

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		this.data = data;
		this.next = next;
		this.previous = previous;
	}


	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (element.equals(this.data)) {
				((RecursiveDoubleLinkedListImpl)this.next).previous = this.previous;
				this.data = this.next.data;
				this.next = this.next.next;
			} else {
				this.next.remove(element);
			}
		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<T>(null, null, this);
			} else {
				this.next.insert(element);
			}
		}

	}

	@Override
	public void insertFirst(T element) {
		if (!isEmpty()) {
			if (element != null) {
				RecursiveDoubleLinkedListImpl<T> head = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
				this.next = head;
				this.data = element;
				this.previous = new RecursiveDoubleLinkedListImpl<>(null, this, null);
			}
		} else {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>(null, null, this);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.data = this.next.data;
			this.next = this.next.next;
			if (this.next != null) {
				((RecursiveDoubleLinkedListImpl<T>)this.next).setPrevious(this);
			}

		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = new RecursiveDoubleLinkedListImpl<>();
			} else {
				((RecursiveDoubleLinkedListImpl<T>)this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
