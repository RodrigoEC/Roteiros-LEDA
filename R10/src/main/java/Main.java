import adt.linkedList.set.SetLinkedListImpl;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        SetLinkedListImpl<Integer> s = new SetLinkedListImpl<>();

        SetLinkedListImpl<Integer> s2 = new SetLinkedListImpl<>();

        s.insert(5);
        s.insert(15);
        s.insert(6);
        s.insert(5);
        s.insert(9);

        s2.insert(5);
        s2.insert(15);
        s2.insert(6);
        s2.insert(5);
        s2.insert(9);

        System.out.println(Arrays.toString(s.toArray()));

        s.removeDuplicates();

        System.out.println(Arrays.toString(s.toArray()));

//        s.concatenate(s2);
//
//        System.out.println(Arrays.toString(s.toArray()));

        s.removeDuplicates();

        System.out.println(Arrays.toString(s.toArray()));

        s.concatenate(s2);

        System.out.println(Arrays.toString(s.toArray()));






    }
}
