package sorting;

public class Verificador<T extends Comparable<T>> {
    public boolean verificador(T[] array, int leftInder, int rightIndex) {
        if (rightIndex < leftInder) {
            return false;
        } else if (array.length != 0 && leftInder >= 0 && rightIndex <= array.length) {
            return true;
        } else {
            return false;
        }
    }
}
