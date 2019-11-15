package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Verificador;

import static util.Util.swap;
/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	Verificador verificar = new Verificador();

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean swapping = true;
		if (verificar.verificador(array, leftIndex, rightIndex)) {
			while(swapping) {
				swapping = false;
				for(int i = leftIndex; i < rightIndex; i++) {
					if (array[i].compareTo(array[i + 1]) > 0) {
						swap(array, i, i + 1);
						swapping = true;
					}
				}
			}
		}

	}







}
