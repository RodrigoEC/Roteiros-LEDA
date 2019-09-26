package com.company;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

    protected RecursiveDoubleLinkedListImpl<T> previous;

    public RecursiveDoubleLinkedListImpl() {

    }

    public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
                                         RecursiveDoubleLinkedListImpl<T> previous) {
        super(data, next);
        this.previous = previous;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (isEmpty()) {
                data = element;
                next = new RecursiveDoubleLinkedListImpl<T>(null, null, this);
            } else {
                next.insert(element);
            }
        }

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
    public void insertFirst(T element) {
        if (element != null) {
            RecursiveDoubleLinkedListImpl<T> head = new RecursiveDoubleLinkedListImpl<>(element, this, new RecursiveDoubleLinkedListImpl<>());
            this = ;

        }

    }

    @Override
    public void removeFirst() {

    }

    @Override
    public void removeLast() {

    }

    public RecursiveDoubleLinkedListImpl<T> getPrevious() {
        return this.previous;
    }

    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
        this.previous = previous;
    }

}
