
package main.java.adt.hashtable.open;

import main.java.adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import main.java.adt.hashtable.hashfunction.HashFunctionOpenAddress;
import main.java.adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {

	}

	@Override
	public void remove(T element) {

	}

	@Override
	public T search(T element) {

		return null;
	}

	@Override
	public int indexOf(T element) {

		return -1;
	}

	private int hashFunction(T element, int probe) {
		int hashIndex = ((HashFunctionOpenAddress<T>) super.hashFunction).hash(element, probe);
		return hashIndex;
	}
}