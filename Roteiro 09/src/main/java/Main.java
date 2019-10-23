import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.open.HashtableElement;
import adt.hashtable.open.HashtableOpenAddressLinearProbingImpl;

public class Main {
    HashtableOpenAddressLinearProbingImpl<HashtableElement> h1 = new HashtableOpenAddressLinearProbingImpl<>(
            10, HashFunctionClosedAddressMethod.DIVISION);

    public static void main(String[] args) {
        HashtableOpenAddressLinearProbingImpl<HashtableElement> h1 = new HashtableOpenAddressLinearProbingImpl<>(
                10, HashFunctionClosedAddressMethod.DIVISION);

        System.out.println();h1.search(new HashtableElement(5));
        h1.insert(new HashtableElement(5));
        h1.insert(new HashtableElement(1));
        h1.insert(new HashtableElement(15));
        h1.insert(new HashtableElement(89));
        h1.insert(new HashtableElement(6));
        h1.insert(new HashtableElement(25));
        h1.insert(new HashtableElement(45));
        h1.insert(new HashtableElement(35));
        h1.insert(new HashtableElement(55));
        h1.insert(new HashtableElement(65));




        System.out.println(h1.indexOf(new HashtableElement(65)));
    }


}
