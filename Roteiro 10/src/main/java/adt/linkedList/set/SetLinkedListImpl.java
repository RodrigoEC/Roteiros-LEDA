package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

import java.util.ArrayList;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

	@Override
	public void removeDuplicates() {
		SingleLinkedListNode<T> auxHead = this.head;

		SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();
		ArrayList<T> list = new ArrayList<>();

		while(!auxHead.isNIL()) {
			T elementHead = auxHead.getData();

			if (!list.contains(elementHead)) {
				newSet.insert(elementHead);
				list.add(elementHead);
			}

			auxHead = auxHead.getNext();
		}

		this.head = newSet.getHead();
	}

	@Override
	public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();

		newSet.concatenate(this);
		newSet.concatenate(otherSet);

		return newSet;
	}

	@Override
	public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
		SetLinkedListImpl<T> newSet = new SetLinkedListImpl<>();

		if (otherSet != null) {
			SingleLinkedListNode<T> auxHead = this.head;


			while(!auxHead.isNIL()) {
				T elementAtual = auxHead.getData();
				if (((SetLinkedListImpl) otherSet).search(elementAtual) != null) {
					newSet.insert(elementAtual);
				}
				auxHead = auxHead.getNext();
			}

		}
		return newSet;
	}

	@Override
	public void concatenate(SetLinkedList<T> otherSet) {
		if (otherSet != null) {
			SingleLinkedListNode<T> auxHead = this.head;

			while(!auxHead.isNIL()) {
				auxHead = auxHead.getNext();
			}

			if (!otherSet.isEmpty()) {
				auxHead.setData(((SetLinkedListImpl<T>) otherSet).getHead().getData());
				auxHead.setNext(((SetLinkedListImpl<T>) otherSet).getHead().getNext());
			}

			this.removeDuplicates();
		}
	}

}
