import java.lang.reflect.Array;
import java.util.Arrays;

public class sort2<T extends Comparable<T>> {

    public void bubbleSort(T[] array, int leftIndex, int rightIndex) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            for (int j = leftIndex; j < rightIndex; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    public void mergeSort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex != rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;
            
            
            mergeSort(array, leftIndex, mid);
            mergeSort(array, mid + 1, rightIndex);
            merge(array, leftIndex, mid, rightIndex);
            
            
            
        }
    }

    private void merge(T[] array, int leftIndex, int mid, int rightIndex) {
        T[] arrayCopy = ( T[])new Comparable[array.length];
        System.arraycopy(array,0, array, 0, array.length);

        int leftIndexCopy = leftIndex;
        int midCopy = mid + 1;

        for(int i = leftIndex; i <= rightIndex; i++) {
            if (leftIndexCopy > mid) {
                array[i] = arrayCopy[midCopy++];
            } else if (midCopy > rightIndex) {
                array[i] = arrayCopy[leftIndexCopy++];
            } else if (arrayCopy[leftIndexCopy].compareTo(arrayCopy[midCopy]) > 0) {
                array[i] = arrayCopy[midCopy++];
            } else {
                array[i] = arrayCopy[leftIndexCopy++];
            }
        }
    }

    public void quickSort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex - 1) {
            T pivot = array[leftIndex];

            int i = leftIndex;
            int j = i + 1 ;

            while(j <= rightIndex) {
                if (array[j].compareTo(pivot) < 0) {
                    i++;
                    swap(array, j, i);
                }
                j++;
            }
            swap(array, leftIndex, i);

            quickSort(array, leftIndex, i - 1);
            quickSort(array, i + 1, rightIndex);
        }
    }


    private void swap(T[] array, int i, int i1) {
        T aux = array[i];
        array[i] = array[i1];
        array[i1] = aux;

    }



}
