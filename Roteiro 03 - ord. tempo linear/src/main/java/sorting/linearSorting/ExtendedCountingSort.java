package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		int[] maximoEminimoArray= maxEMin(array);
		Integer menorElemento = maximoEminimoArray[1];
		Integer maiorElemento = maximoEminimoArray[0];


		Integer[] arrayDeRepeticoes = new Integer[maiorElemento - menorElemento + 1];
		Integer[] arrayAuxiliar = new Integer[array.length];


		// Todos os elementos do array de repeticoes agora são 0, não mais null.
		for (int i = 0; i < arrayDeRepeticoes.length; i++) {
			arrayDeRepeticoes[i] = 0;
		}

		// Os elementos do array de entrada -menos o menor elemento do array são usados como indice para o arrayDeRepeticao.
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayDeRepeticoes[array[i] - menorElemento]++;
		}

		// os elementos no array de repetições recebem como adição o elemento anterior da lista
		// Ex: {1, 5, 3} vira {1, 6, 9}
		for(int i = 1; i < arrayDeRepeticoes.length; i++) {
			arrayDeRepeticoes[i] += arrayDeRepeticoes[i - 1];
		}

		//Os elementos do array  - 1sãoo mapeados de tras pra frente para o array de repeticoes, onde o elemento que correponde
		// a esse indice decresce uma unidade e esse elemento eh usado como indice de onde o elemento do array de entrada será colocado
		// no array auxiliar
		for (int i = rightIndex; i >= leftIndex; i--) {

			arrayDeRepeticoes[array[i] - menorElemento]--;
			Integer indiceElemento = arrayDeRepeticoes[array[i] - menorElemento];
			arrayAuxiliar[indiceElemento] = array[i];
		}

		// Os elementos do array auxiliar são mapeados copiados para o array de entrada.
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = arrayAuxiliar[i];
		}

	}

	private int[] maxEMin(Integer[] array) {
		int[] maxEmin = new int[2];
		if (array.length > 0) {
			Integer max = array[0];
			Integer min = array[0];

			for (int i = 1; i < array.length; i++) {
				if (array[i] > max) {
					max = array[i];
				} else if (array[i] < min) {
					min = array[i];
				}
			}
			maxEmin[0] = max;
			maxEmin[1] = min;

		}

		return maxEmin;
	}















}
