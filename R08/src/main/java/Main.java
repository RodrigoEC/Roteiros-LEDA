import adt.hashtable.closed.HashtableClosedAddressImpl;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;

public class Main {
    public static void main(String[] args) {
        HashtableClosedAddressImpl<Integer> i = new HashtableClosedAddressImpl<>(10, HashFunctionClosedAddressMethod.DIVISION);

        i.insert(40);

        i.insert(10);
        System.out.println(i.getCOLLISIONS());

//        System.out.println(i.search(4));
        i.insert(4);
        System.out.println(i.getCOLLISIONS());
        i.remove(15);
        i.insert(15);

        System.out.println(i.getCOLLISIONS());
        i.insert(26);
        System.out.println(i.getCOLLISIONS());
        i.insert(37);
//        System.out.println(i.search(4));
        System.out.println(i.getCOLLISIONS());
    }
}
