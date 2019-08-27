package sorting;

import sorting.simpleSorting.BubbleSort;
import sorting.simpleSorting.InsertionSort;
import sorting.simpleSorting.SelectionSort;
import sorting.variationsOfBubblesort.SimultaneousBubblesort;

import java.util.Arrays;

public class Main {
   public static void main(String[] args) {
      InsertionSort<Integer> i = new InsertionSort<>();
      SelectionSort<Integer> s = new SelectionSort<>();
      BubbleSort<Integer> b = new BubbleSort<>();
      SimultaneousBubblesort si = new SimultaneousBubblesort();
      Integer[] array = new Integer[] {30, 28, 7, 29, 11, 26, 4, 22, 23,
              31 };

      b.sort(array, 0, 9);
      System.out.println(Arrays.toString(array));
      array = new Integer[] {30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };

      i.sort(array, 0, 9);
      System.out.println(Arrays.toString(array));
      array = new Integer[] {30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };


      s.sort(array, 0, 9);
      System.out.println(Arrays.toString(array));
      array = new Integer[] {30, 28, 7, 29, 11, 26, 4, 22, 23, 31 };

      si.sort(array, 0, 9);
      System.out.println(Arrays.toString(array));
      System.out.println("vacas");


   }
}
