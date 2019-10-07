package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (super.isFull()) {
			throw new HashtableOverflowException();
		}

		if (element != null && this.search(element) == null) {
			this.elements++;
			boolean swapping = false;
			int probe = 0;

			while (!swapping) {
				int i = hashFunction(element, probe);

				if (table[i] != null && !this.deletedElement.equals(this.table[i])) {
					super.COLLISIONS++;

				} else if (this.table[i] == null || super.deletedElement.equals(table[i])) {
					this.table[i] = element;
					swapping = true;
				}
				probe++;
			}

		}
	}

	@Override
	public void remove(T element) {

		if (element != null && this.search(element) != null && this.capacity() > 0) {
			this.elements--;
			boolean swapping = false;
			int probe = 0;


			while(!swapping) {
				int i = hashFunction(element, probe);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						this.table[i] = super.deletedElement;
						swapping = true;

					} else if (probe > 0) {
						this.COLLISIONS--;
					}
				}
				probe++;
			}
		}

	}

	@Override
	public T search(T element) {
		T resultado = null;

		if (element != null && this.capacity() > 0) {
			int probe = 0;

			while(probe < this.capacity() && resultado == null) {
				int i = hashFunction(element, probe);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						resultado = element;
					}
				}
				probe++;
			}
		}

		return resultado;
	}

	@Override
	public int indexOf(T element) {
		int resultado = -1;

		if (element != null && this.capacity() > 0 && this.search(element) != null) {
			int probe = 0;

			while (probe < this.capacity() && resultado == -1) {
				int i = hashFunction(element, probe);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						resultado = i;
					}
				}
				probe++;
			}
		}
		return resultado;
	}
	private int hashFunction(T element, int probe) {
		return ((HashFunctionOpenAddress<T>) super.hashFunction).hash(element, probe);
	}
}
