
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

		if (element != null && !this.isFull()) {
			int contador = 0;

			int i = hashFunction(element, contador);

			while(contador < this.capacity() && this.table[i] == null) {
				i = hashFunction(element, contador);

				if (this.table[i] == null || super.deletedElement.equals(table[i])) {
					this.table[i] = element;
					this.elements++;
				}
				contador++;
			}

		}

	}

	@Override
	public void remove(T element) {
		if (element != null && this.search(element) != null && this.capacity() > 0) {
			int contador = 0;

			int i = hashFunction(element, contador);

			while(contador < this.table.length && this.table[i] != null) {
				i = hashFunction(element, contador);

				if(element.equals(this.table[i])) {
					this.table[i] = super.deletedElement;
					this.elements--;
					return;

				}
				contador++;
			}

		}

	}

	@Override
	public T search(T element) {
		T resultado = null;
		if (element != null && this.table.length > 0) {
			int contador = 0;

			while(contador < this.table.length) {
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

		if (element != null && this.table.length > 0 && this.search(element) != null) {
			int contador = 0;

			while (contador < this.table.length) {
				int i = hashFunction(element, contador);

				if (this.table[i].equals(element)) {
					resultado = i;
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
