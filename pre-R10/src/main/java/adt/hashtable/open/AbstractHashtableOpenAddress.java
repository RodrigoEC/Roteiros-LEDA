package main.java.adt.hashtable.open;

import main.java.util.Util;
import main.java.adt.hashtable.AbstractHashtable;

public abstract class AbstractHashtableOpenAddress<T extends Storable> extends
		AbstractHashtable<T> {

	protected final DELETED deletedElement = new DELETED();
	private int tableSize;

	public AbstractHashtableOpenAddress(int size) {
		this.tableSize = size;
	}

	@Override
	protected void initiateInternalTable(int size) {
		this.table = Util.<Storable> makeArray(size);
	}
}
