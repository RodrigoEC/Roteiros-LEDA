import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Integer[] array = {2, 15, 75, 75, 12, 100, 75, 75};
        Integer[] array2 = {1,2,3,4,5,7};

        sort2<Integer> s = new sort2<>();
        Sort<Integer> s1 =new Sort<>();

        //s.quickSort(array, 0, 5);

        System.out.println(s1.buscarBinaria(array, 75, 0, 5, 0));
        System.out.println(Arrays.toString(array2));

    }
}
