import adt.hashtable.closed.HashtableClosedAddressImpl;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Main {
    public static void main(String[] args) {
        HashtableClosedAddressImpl<Integer> i = new HashtableClosedAddressImpl<>(10, HashFunctionClosedAddressMethod.DIVISION);

        i.insert(4);
        i.insert(40);
        i.insert(15);
        i.insert(48);
        i.insert(10);

        System.out.println(i.search(4));

        i.remove(4);
        System.out.println(i.search(4));
    }
}
