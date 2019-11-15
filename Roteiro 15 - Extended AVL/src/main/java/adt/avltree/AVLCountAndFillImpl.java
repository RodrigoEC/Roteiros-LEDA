package adt.avltree;

import adt.bst.BSTNode;

import java.util.ArrayList;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.sort;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T> {

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
      if (array != null) {
         esvaziaAVL();
         sort(array);

         ArrayList<T[]> arrays = new ArrayList<>();
         if (array.length > 2) {
            int middle = array.length / 2;
            insert(array[middle]);
            arrays.add(array);
            addArray(arrays, array);
            int contadorEsquerda = 1;
            insert(getMiddle(arrays.get(contadorEsquerda++)));
            insert(getMiddle(arrays.get(contadorEsquerda++)));

            int contadorDireita = arrays.get(1).length + 2;
            int contadorLimite = arrays.get(1).length + 2;

            int fimArray = arrays.size();
            for (int i = 0; i < arrays.size() - 1; i++) {
               if (i < arrays.get(1).length + 2) {
                  if (arrays.get(i + 1).length ==0) {
                     contadorDireita++;
                  }
               } else if (i > contadorDireita) {
                  if (arrays.get(i + 1).length ==0) {
                     fimArray -= 2;
                  }
               }
            }



            while (contadorDireita < fimArray || contadorEsquerda < contadorLimite) {
               if (arrays.get(contadorEsquerda + 1).length ==0) {
                  contadorEsquerda += 2;
               }
               if (arrays.get(contadorDireita + 1).length ==0) {
                  contadorDireita += 2;
               }

               if (contadorEsquerda < contadorLimite) {
                  insert(getMiddle(arrays.get(contadorEsquerda++)));

               }
               if (contadorEsquerda < contadorLimite) {
                  insert(getMiddle(arrays.get(contadorEsquerda++)));
               }

               if (contadorDireita < fimArray) {
                  insert(getMiddle(arrays.get(contadorDireita++)));

               }
               if (contadorDireita < fimArray) {
                  insert(getMiddle(arrays.get(contadorDireita++)));

               }
            }

            for (int i = 0; i < arrays.size() - 1; i++) {
               if (arrays.get(i + 1).length == 0) {
                  insert(getMiddle(arrays.get(i)));
               }
            }
         } else {
            for (T elem : array) {
               insert(elem);
            }
         }
      }
   }

   public T getMiddle(T[] array) {
      int middle = array.length / 2;
      return array[middle];
   }

   public void addArray(ArrayList lista, T[] array) {
      if (array.length > 1) {
         int middle = array.length / 2;
         T[] arrayEsquerda = copyOfRange(array, 0, middle);
         T[] arrayDireita = copyOfRange(array, middle + 1, array.length);

            lista.add(arrayEsquerda);

            lista.add(arrayDireita);
         addArray(lista, arrayEsquerda);
         addArray(lista, arrayDireita);

      }
   }

   private void esvaziaAVL() {
      this.root = new BSTNode<>();
      this.RLcounter = 0;
      this.LLcounter = 0;
      this.LRcounter = 0;
      this.RRcounter = 0;
   }

}
