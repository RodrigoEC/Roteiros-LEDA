
package main.java.adt.hashtable.open;

import main.java.adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import main.java.adt.hashtable.hashfunction.HashFunctionLinearProbing;
import main.java.adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (super.isFull()) {
			throw new HashtableOverflowException();
		}

		if (element != null && !this.isFull() && this.search(element) == null) {
			this.elements++;
			boolean swapping = false;
			int contador = 0;

			while(!swapping) {
				int i = hashFunction(element, contador);

				if (table[i] != null && !this.deletedElement.equals(this.table[i])) {
					super.COLLISIONS++;

				} else if (this.table[i] == null || super.deletedElement.equals(table[i])) {
					this.table[i] = element;
					swapping = true;
				}
				contador++;
			}

		}

	}

	@Override
	public void remove(T element) {
		if (element != null && this.search(element) != null && this.capacity() > 0) {
			this.elements--;

			boolean swapping = false;
			int contador = 0;


			while(!swapping) {
				int i = hashFunction(element, contador);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						this.table[i] = super.deletedElement;
						swapping = true;
					} else if (contador > 0) {
						this.COLLISIONS--;
					}
				}
				contador++;
			}
		}

	}

	@Override
	public T search(T element) {
		T resultado = null;

		if (element != null && this.capacity() > 0) {
			int contador = 0;

			while(contador < this.capacity() && resultado == null) {
				int i = hashFunction(element, contador);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						resultado = element;
					}
				}
				contador++;
			}
		}

		return resultado;
	}

	@Override
	public int indexOf(T element) {
		int resultado = -1;

		if (element != null && this.capacity() > 0 && this.search(element) != null) {
			int contador = 0;

			while (contador < this.capacity() && resultado == -1) {
				int i = hashFunction(element, contador);

				if (this.table[i] != null) {
					if (element.equals(this.table[i])) {
						resultado = i;
					}
				}
				contador++;
			}
		}
		return resultado;
	}

	private int hashFunction(T element, int probe) {
		return ((HashFunctionOpenAddress<T>) super.hashFunction).hash(element, probe);
	}

}
