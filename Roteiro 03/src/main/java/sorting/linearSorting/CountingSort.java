package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = maior(array, leftIndex, rightIndex);
		Integer[] arrayDeRepeticoes = new Integer[maior + 1];
		Integer[] arrayAuxiliar = new Integer[array.length];

		// Todos os elementos do array de repeticoes agora são 0, não mais null.
		for (int i = 0; i < arrayDeRepeticoes.length; i++) {
			arrayDeRepeticoes[i] = 0;
		}

		// Os elementos do array de entrada - 1 são usados como indice do array de repetições desses elementos.
		for (int i = leftIndex; i <= rightIndex; i++) {
			arrayDeRepeticoes[array[i]]++;
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
			arrayDeRepeticoes[array[i]]--;
			Integer indiceElemento = arrayDeRepeticoes[array[i]];
			arrayAuxiliar[indiceElemento] = array[i];
		}

		// Os elementos do array auxiliar são mapeados copiados para o array de entrada.
		for (int i = leftIndex; i <= rightIndex; i++) {
			array[i] = arrayAuxiliar[i];
		}







	}


	private static Integer maior(Integer[] array, int leftIndex, int rightIndex) {
		Integer maior = 0;
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] >= maior) {
				maior = array[i];
			}
		}
		return maior;
	}


}
