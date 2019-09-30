package adt.hashtable.hashfunction;


/**
 * This class is responsible to create hash functions suitable to each kind of
 * hash table.
 * 
 * @author Adalberto
 *
 */
public class HashFunctionFactory<T> {

	public static adt.hashtable.hashfunction.HashFunction createHashFunction(
			adt.hashtable.hashfunction.HashFunctionClosedAddressMethod method, int tableSize) {
		adt.hashtable.hashfunction.HashFunction result = null;
		switch (method) {
		case DIVISION:
			result = new HashFunctionDivisionMethod(tableSize);
			break;
		case MULTIPLICATION:
			result = new adt.hashtable.hashfunction.HashFunctionMultiplicationMethod(tableSize);
			break;
		}

		return result;
	}

}
