package adt.avltree;

import java.util.Arrays;

import adt.bst.BSTNode;
import adt.bt.Util;

import static java.util.Arrays.sort;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (Math.abs(balance) > 1) {
			if (calculateBalance(node) > 1 && calculateBalance(getLeft(node)) >= 0) { //LL
				this.LLcounter++;
				rightRotation(node);
			} else if (calculateBalance(node) < -1 && calculateBalance(getRight(node)) <= 0) { //RR
				this.RRcounter++;
				leftRotation(node);
			} else if (calculateBalance(node) > 1 && calculateBalance(getLeft(node)) < 0) { //LR
				this.LRcounter++;
				leftRotation(getLeft(node));
				rightRotation(node);
			} else if (calculateBalance(node) < -1 && calculateBalance(getRight(node)) > 0) { //RL
				this.RLcounter++;
				rightRotation(getRight(node));
				leftRotation(node);
			}
		}
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	//adiciona os elementos na arvore sempre pegando o elemento do meio do array (ordenado);
	@Override
	public void fillWithoutRebalance(T[] array) {
		esvaziaAVL();
		sort(array);
		if (array.length > 0) {
			int left = array.length / 2;
			int right = array.length / 2;
			insert(array[left]);
			fillWithoutRebalance(array, left, right);

		}
	}

	private void fillWithoutRebalance(T[] array, int left, int right) {
		if (array.length == 1) {
			insert(array[i]);
		}

	}

	private int factorial(int height) {
		int contador = 0;
		int soma = 0;
		while (contador != height) {
			contador++;
			soma += 2 * contador;
		}
	}


	private void esvaziaAVL() {
		this.root = new BSTNode<>();
	}

}
