import java.lang.reflect.Array;
import java.util.*;

public class Sort<T extends Comparable<T>>{


    public void bubbleSort(T[] array) {
        boolean swapping = true;

        while (swapping) {
            swapping = false;

            for (int i = 0; i < array.length - 1; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                    swapping = true;
                }
            }
        }
    }



    public void recursiveBubbleSort(T[] array,  int fim) {
        if (fim != 0) {
            for (int i = 0; i < fim; i++) {
                if (array[i].compareTo(array[i + 1]) > 0) {
                    swap(array, i, i + 1);
                }
            }
            recursiveBubbleSort(array, fim - 1);
        }
    }

    public void gnomeSort(T[] array) {
        int pos = 1;

        while(pos < array.length) {
            if (array[pos].compareTo(array[pos - 1]) >= 0) {
                pos++;
            } else {
                swap(array,pos, pos - 1);
                if (pos > 1) {
                    pos--;
                } else {
                    pos++;
                }
            }
        }
    }


    public void selectionSort(ArrayList<Integer> array) {
        for (int i = 0; i < array.size(); i++) {
            int menor = i;

            for (int j = i + 1; j < array.size(); j++) {
                if (array.get(j).compareTo(array.get(menor)) < 0) {
                    menor = j;
                }
            }
            swap(array, i, menor);
        }
    }


    public void recursiveSelectionSort(T[] array, int leftIndex, int rightIndex) {
        if (rightIndex != leftIndex) {
            int menor = leftIndex;
            for (int i = leftIndex + 1; i <= rightIndex; i++) {
                if (array[i].compareTo(array[menor]) < 0) {
                    menor = i;
                }
            }
            swap(array, leftIndex, menor);
            recursiveSelectionSort(array, leftIndex + 1, rightIndex);
        }
    }

    private void swap(ArrayList<Integer> array, int i, int i1) {
        Integer aux = array.get(i);
        array.add(i, array.get(i1));
        array.add(i1, aux);

    }

    public void insertionSort(T[] array) {
        for (int i = 1; i < array.length; i++) {
            T key = array[i];
            int j = i - 1;

            while(j >= 0 && key.compareTo(array[j]) < 0) {
                swap(array, j, j + 1);
                j--;
            }
        }
    }

    public void recursvieInsertionSort(T[] array, int inicio) {
        if (inicio != array.length) {
            T key = array[inicio];
            int j = inicio - 1;

            while(j >= 0 && key.compareTo(array[j]) < 0) {
                swap(array, j, j + 1);
                j--;
            }

            recursvieInsertionSort(array, inicio + 1);
        }
    }


    public void mergerSort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {

            int midIndex = (leftIndex + rightIndex ) / 2;

            mergerSort(array, leftIndex, midIndex);
            mergerSort(array, midIndex + 1, rightIndex);
            merge(array, leftIndex, midIndex, rightIndex);
        } else {
            return;
        }
    }

    private void merge(T[] array, int leftIndex, int midIndex, int rightIndex) {
        T[] copia = (T[])new Comparable[array.length];
        System.arraycopy(array,0, copia, 0, array.length);

        int leftIndexCopy = leftIndex;
        int midIndexCopy = midIndex + 1;

        for (int i = leftIndex; i <= rightIndex; i++) {
            if (leftIndexCopy > midIndex) {
                array[i] = copia[midIndexCopy++];
            } else if (midIndexCopy > rightIndex) {
                array[i] = copia[leftIndexCopy++];
            } else if (copia[leftIndexCopy].compareTo(copia[midIndexCopy]) < 0) {
                array[i] = copia[leftIndexCopy++];

            } else {
                array[i] = copia[midIndexCopy++];
            }
        }
    }

    public void quickSort(T[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            T pivot = array[leftIndex];

            int i = leftIndex;
            int j = i + 1;

            while(j <= rightIndex) {
                if (array[j].compareTo(pivot) < 0) {
                    i++;
                    swap(array, i, j);
                }
                j++;
            }
            swap(array, i, leftIndex);

            quickSort(array, i + 1, rightIndex);
            quickSort(array, leftIndex, i - 1);
        }

    }


    public void bucketSort(Integer[] array) {
        ArrayList<Integer>[] buckets = new ArrayList[10];

        Integer maiorElemento = maiorElemento(array);

        for(int i = 0; i < array.length; i++) {
            int indice = array[i] * 10 / maiorElemento;

            buckets[i].add(array[i]);
        }

        for(ArrayList<Integer> arrayy: buckets) {
            selectionSort(arrayy);
        }

        int index = 0;
        for(int i = 0; i < buckets.length; i++) {

            if (buckets[i].size() > 1) {
                for (int j = 0; j < buckets[i].size(); j++) {
                    ArrayList<Integer> bucket = buckets[i];
                    array[index++] = bucket.get(j);
                }
            } else {
                array[index++] = buckets[i].get(0);
            }
        }

    }

    public int buscarBinaria(T[] array, T elemento, int leftIndex, int rightIndex, int repeticoes) {
        int rep = repeticoes;
        int i = 0;
        if (leftIndex >= rightIndex) {
            if (array[leftIndex].compareTo(elemento) == 0) {
                return 1;
            }
        } else {
            int medio = (leftIndex + rightIndex) / 2;

            i += buscarBinaria(array, elemento, leftIndex, medio, rep);
            rep += buscarBinaria(array, elemento, medio + 1, rightIndex, rep);

            rep += i;


        }

        return rep;
    }

    public int buscaBinariaOrdenado(T[] array, T k, int leftIndex, int rightIndex) {
        int mid = array.length / 2;

        int resultado;
        if (array[mid].compareTo(k) == 0) {
            resultado = mid;
        } else if (array[mid].compareTo(k) < 0) {
            resultado = buscaBinariaOrdenado(array, k, mid + 1, rightIndex);
        } else {
            resultado = buscaBinariaOrdenado(array, k, leftIndex, mid - 1);
        }

        return resultado;
    }



    public Integer maiorElemento(Integer[] array) {
        Integer maiorElemento = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(maiorElemento) > 0) {
                maiorElemento = array[i];
            }
        }

        return maiorElemento;
    }


    private void swap(T[] array, int i, int i1) {
        T aux = array[i];
        array[i] = array[i1];
        array[i1] = aux;

    }

}
