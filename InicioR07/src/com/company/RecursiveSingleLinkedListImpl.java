package com.company;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

    private static final int ZERO = 0;

    protected T data;
    protected RecursiveSingleLinkedListImpl<T> next;

    public RecursiveSingleLinkedListImpl() {

    }

    public RecursiveSingleLinkedListImpl(T data,
                                         RecursiveSingleLinkedListImpl<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public boolean isEmpty() {
        return (this.data == null);
    }

    @Override
    public int size() {
        if (this.isEmpty()) {
            return 0;
        } else {
            return 1 + size();
        }

    }

    @Override
    public T search(T element) {
        T resultado = null;

        if (element != null) {
            if (element.equals(this.data)) {
                resultado = element;
            } else {
                this.next.search(element);
            }
        }
        return resultado;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (isEmpty()) {
                data = element;
                next = new RecursiveSingleLinkedListImpl<T>();
            } else {
                next.insert(element);
            }
        }
    }

    @Override
    public void remove(T element) {
        if(!isEmpty() && element != null) {
            if(element.equals(this.data)) {
                this.data = this.next.data;
                this.next = this.next.next;
            } else {
                next.remove(element);
            }
        }
    }

    @Override
    public T[] toArray() {
        ArrayList<T> arraySaida = new ArrayList<>();
        toArray(arraySaida, this);

        T[] arrayS = (T[]) arraySaida.toArray();
        return arrayS;
    }

    private void toArray(ArrayList<T> arraySaida, RecursiveSingleLinkedListImpl<T> tRecursiveSingleLinkedList) {
        if (!isEmpty()) {
            arraySaida.add(this.data);
            toArray(arraySaida, this.next);
        }
    }



    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RecursiveSingleLinkedListImpl<T> getNext() {
        return next;
    }

    public void setNext(RecursiveSingleLinkedListImpl<T> next) {
        this.next = next;
    }

}
