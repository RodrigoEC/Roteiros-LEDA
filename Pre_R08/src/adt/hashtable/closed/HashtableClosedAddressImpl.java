package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

import static java.util.Objects.hash;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		int num = number;

		if (num % 2 == 0) {
			num++;
		}

		while (!Util.isPrime(num)) {
			num += 2;
		}

		return num;

	}

	@Override
	public void insert(T element) {
		if (element != null && this.search(element) == null) {

			int i = ((HashFunctionClosedAddress) this.hashFunction).hash(element);

			if (this.table[i] == null) {
				this.table[i] = new LinkedList<>();


			} else {
				this.COLLISIONS++;
			}

			this.elements++;
			((LinkedList<T>) this.table[i]).add(element);
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && this.search(element) != null && !this.isEmpty()) {
			int i = ((HashFunctionClosedAddress) this.hashFunction).hash(element);

			if (this.table[i] != null) {
				if (((LinkedList<T>) this.table[i]).size() > 1) {
					this.COLLISIONS--;
				}
				this.elements--;
				((LinkedList<T>) this.table[i]).remove(element);
			}

		}

	}

	@Override
	public T search(T element) {
		if (element != null && !this.isEmpty()) {
			int i = ((HashFunctionClosedAddress) this.hashFunction).hash(element);

			T result = null;

			if (this.table[i] != null) {
				for (T elemento: ((LinkedList<T>)table[i])) {
					if (elemento.equals(element)) {
						result = elemento;
					}
				}
			}
			return result;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int i = ((HashFunctionClosedAddress) this.hashFunction).hash(element);

			int result = -1;
			if (this.table[i] != null) {
				for (T elemento: ((LinkedList<T>)table[i])) {
					if (elemento.equals(element)) {
						result = i;
					}
				}
			}
			return result;
		}
	}


}
