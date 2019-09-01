package sorting.divideAndConquer;

import sorting.AbstractSorting;
import sorting.Verificador;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
         if (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;

            sort(array, leftIndex, middleIndex);
            sort(array, middleIndex + 1, rightIndex);
            merge(array, leftIndex, middleIndex, rightIndex);
         }
   }

   private void merge(T[] array, int leftIndex, int middleIndex, int rightIndex) {
      T[] copy = (T[]) new Comparable[array.length];
      System.arraycopy(array, 0, copy, 0, array.length);

      int leftCopyIndex = leftIndex;
      int middleCopyIndex = middleIndex + 1;

      for (int i = leftIndex; i <= rightIndex; i++) {
         if (leftCopyIndex > middleIndex) {
            array[i] = copy[middleCopyIndex++];
         } else if (middleCopyIndex > rightIndex) {
            array[i] = copy[leftCopyIndex++];
         } else if (copy[leftCopyIndex].compareTo(copy[middleCopyIndex]) < 0) {
            array[i] = copy[leftCopyIndex++];
         } else {
            array[i] = copy[middleCopyIndex++];
         }
      }

   }
}
