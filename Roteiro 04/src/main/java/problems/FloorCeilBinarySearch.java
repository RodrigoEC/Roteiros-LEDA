package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	public Integer floor(Integer[] array, Integer x) {
		if (array.length == 0 || x.compareTo(array[0]) < 0) {
			return null;
		} else {
			return floor(array, x, 0, array.length - 1);
		}
	}

	private Integer floor(Integer[] array, Integer k, int inicio, int fim) {
		int meio = (inicio + fim) / 2;

		if (inicio == fim) {
			return array[fim];
		}

		int resultado;
		if (array[meio + 1].compareTo(k) < 0) {
			resultado = floor(array, k, meio + 1, fim);

		} else if (array[meio + 1].compareTo(k) > 0){
			resultado = floor(array, k, inicio, meio);

		} else {
			resultado = array[meio];
		}
		return resultado;
	}

	@Override
	public Integer ceil(Integer[] array, Integer x) {
		if (array.length == 0 || x.compareTo(array[array.length - 1]) > 0) {
			return null;
		} else {
			return ceil(array, x, 0, array.length - 1);
		}
	}

	private Integer ceil(Integer[] array, Integer k, int inicio, int fim) {
		int meio = (inicio + fim) / 2;
		int resultado;

		if (inicio == fim) {
			return  array[fim];
		}

		if (array[meio].compareTo(k) > 0) {
			resultado = ceil(array, k, inicio, meio);
		} else if (array[meio].compareTo(k) < 0) {
			resultado = ceil(array, k, meio + 1, fim);
		} else {
			resultado = array[meio];
		}
		return resultado;





	}

}
