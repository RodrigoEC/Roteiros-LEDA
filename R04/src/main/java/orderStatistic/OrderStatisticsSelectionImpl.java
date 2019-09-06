package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no 
	 *   array original
	 * - Nenhum array auxiliar deve ser criado e utilizado.
	 * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos menores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   como o selectionsort mas sem modificar nenhuma posicao do array).
	 * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null. 
	 * - Considerar que k varia de 1 a N 
	 * - Sugestao: o uso de recursao ajudara sua codificacao.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		if (array.length < k || array.length == 0 || k <= 0) {
			return null;
		}

		T menorElemento = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(menorElemento) < 0) {
				menorElemento = array[i];
			}
		}

		return ordenaStatistics(array, k, menorElemento);

	}

	private T ordenaStatistics(T[] array, int k, T menor) {
		T elementoK;

		if (k == 1) {
			elementoK = menor;
		} else {
			T menorElemento = getMenor(array, menor);
			elementoK = ordenaStatistics(array, k - 1, menorElemento);
		}

		return elementoK;
	}

	private T getMenor(T[] array, T menor) {
		T menorElemento = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i].compareTo(menorElemento) <= 0 && array[i].compareTo(menor) > 0) {
				menorElemento = array[i];
			}
		}
		return menorElemento;
	}


}
