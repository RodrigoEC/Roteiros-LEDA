package sorting.divideAndConquer;

import sorting.AbstractSorting;
import sorting.Verificador;

import static util.Util.swap;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private Verificador verificador = new Verificador();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
			if (leftIndex < rightIndex) {
				int pivot = dividir(array, leftIndex, rightIndex);
				sort(array, leftIndex, pivot - 1);
				sort(array, pivot + 1, rightIndex);
			}

	}

	private int dividir(T[] array, int leftIndex, int rightIndex) {
		int i = leftIndex;

		for (int j = leftIndex + 1; j <= rightIndex; j++) {
			if (array[leftIndex].compareTo(array[j]) > 0) {
				i++;
				swap(array, i, j);
			}
		}

		swap(array, i, leftIndex);
		return i;
	}
}
