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

        s.concatenate(null);
        s.intersection(s2);

        System.out.println(Arrays.toString(s.intersection(null).toArray()));

        System.out.println(Arrays.toString(s.union(s2).toArray()));

        SetLinkedListImpl<Integer> s3 = new SetLinkedListImpl<>();

        System.out.println(Arrays.toString(((SetLinkedListImpl) s.union(s3)).toArray()));
        s2.insert(0);
        System.out.println(Arrays.toString(((SetLinkedListImpl) s.union(s2)).toArray()));





    }
}
