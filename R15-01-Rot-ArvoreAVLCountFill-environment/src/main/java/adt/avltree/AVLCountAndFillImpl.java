package adt.avltree;

import adt.bst.BSTNode;

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

	@Override
	public void fillWithoutRebalance(T[] array) {
		esvaziaAVL();
		sort(array);


		if (array.length > 0) {
			int left = array.length / 2;
			int right = array.length / 2;

			insert(array[left]);

 			while (size() < array.length) {
				fillWithoutRebalance(array, 0, left -1);
				fillWithoutRebalance(array, right + 1, array.length -1);
			}


		}
	}

	private void fillWithoutRebalance(T[] array, int start, int end) {
		 int i = (start + end) / 2;
		 insert(array[i]);
		 int s = size();
		 if (s + 1 > kindaFactorial(height() + 1)) {
		 	if (i != start) {
				fillWithoutRebalance(array, start, i - 1);

			}
			if (i != end) {
				fillWithoutRebalance(array, i + 1, end);
			}
		 }
	}

	public int kindaFactorial(int height) {
		int contador = 0;
		int soma = 0;
		while (contador < height) {
			soma += Math.pow(2, contador++);

		}
		return soma;
	}


	private void esvaziaAVL() {
		this.root = new BSTNode<>();
	}


}
