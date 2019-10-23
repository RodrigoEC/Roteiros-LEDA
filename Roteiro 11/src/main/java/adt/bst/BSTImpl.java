package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

   protected BSTNode<T> root;

   public BSTImpl() {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot() {
      return this.root;
   }

   @Override
   public boolean isEmpty() {
      return root.isEmpty();
   }

   @Override
   public int height() {
      int resultado = -1;
      if (!this.root.isEmpty()) {
         resultado = height(this.root);
      }
      return resultado;
   }

   private int height(BSTNode<T> root) {
      if (!root.isEmpty()) {
         return 1 + (Math.max(height((BSTNode<T>) root.getLeft()), height((BSTNode<T>) root.getRight())));
      } else {
         return -1;
      }
   }

   @Override
   public BSTNode<T> search(T element) {
      BSTNode<T> resultado = new BSTNode<>();
      if (element != null) {
         BSTNode<T> elemAtual = this.root;

         while (!elemAtual.isEmpty() && resultado.isEmpty()) {
            if (element.equals(elemAtual.getData())) {
               resultado = elemAtual;

            } else if (element.compareTo(elemAtual.getData()) > 0) {
               elemAtual = (BSTNode<T>) elemAtual.getRight();

            } else if (element.compareTo(elemAtual.getData()) < 0) {
               elemAtual = (BSTNode<T>) elemAtual.getLeft();
            }
         }
      }

      return resultado;
   }

   @Override
   public void insert(T element) {
      if (element != null) {
         insert(this.root, element);
      }
   }

   private void insert(BSTNode<T> node, T element) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new BSTNode<T>());
         node.setRight(new BSTNode<T>());
         node.getLeft().setParent(node);
         node.getRight().setParent(node);
         ;
      } else {
         if (element.compareTo(node.getData()) < 0) {
            insert(getLeft(node), element);
         } else if (element.compareTo(node.getData()) > 0) {
            insert(getRight(node), element);
         }
      }
   }

   @Override
   public BSTNode<T> maximum() {
      return maximum(this.root);

   }

   private BSTNode<T> maximum(BSTNode<T> node) {
      BSTNode<T> resultado = null;

      if (node != null && !node.isEmpty()) {
         resultado = node;

         while (!resultado.getRight().isEmpty()) {
            resultado = getRight(resultado);
         }
      }
      return resultado;

   }

   @Override
   public BSTNode<T> minimum() {
      return minimum(this.root);

   }

   private BSTNode<T> minimum(BSTNode<T> node) {
      BSTNode<T> resultado = null;

      if (!node.isEmpty()) {
         resultado = node;

         while (!resultado.getLeft().isEmpty()) {
            resultado = getLeft(resultado);
         }
      }
      return resultado;
   }

   @Override
   public BSTNode<T> sucessor(T element) {
      BSTNode<T> resultado = search(element);
      if (!resultado.isEmpty()) {
         if (!getRight(resultado).isEmpty()) {
            resultado = minimum(getRight(resultado));
         } else {
            BSTNode<T> anterior = getParent(resultado);

            while (anterior != null && menorQue(anterior, resultado)) {
               resultado = anterior;
               anterior = getParent(anterior);
            }

            resultado = anterior;
         }
      } else {
         resultado = null;
      }

      return resultado;
   }

   @Override
   public BSTNode<T> predecessor(T element) {
      BSTNode<T> resultado = null;
      BSTNode<T> elemNode = search(element);

      if (!elemNode.isEmpty()) {
         resultado = maximum(getLeft(elemNode));
         if (resultado == null) {
            resultado = getParent(elemNode);

            while (resultado != null && menorQue(elemNode, resultado)) {
               resultado = getParent(resultado);
            }
         }
      }
      return resultado;
   }

   private boolean menorQue(BSTNode<T> node1, BSTNode<T> node2) {
      return (node1.getData().compareTo(node2.getData()) < 0);
   }

   @Override
   public void remove(T element) {
      BSTNode<T> node = search(element);

      if (element != null && !node.isEmpty()) {
         if (node.isLeaf()) {
            node.setData(null);
         } else if (hasOneChild(node)) {
            removeOneChild(node);
         } else {
            removeTwoChildren(node);
         }
      }
   }

   private void removeOneChild(BSTNode<T> node) {
      if (node.getParent() != null) {
         if (!getParent(node).getLeft().equals(node)) {
            if (!getLeft(node).isEmpty()) {
               getParent(node).setRight(node.getLeft());
               node.getLeft().setParent(node.getParent());

            } else {
               getParent(node).setRight(node.getRight());
               node.getRight().setParent(node.getParent());
            }
         } else {
            if (!getLeft(node).isEmpty()) {
               getParent(node).setLeft(node.getLeft());
               node.getLeft().setParent(node.getParent());
            } else {
               getParent(node).setLeft(node.getRight());
               node.getRight().setParent(node.getParent());
            }
         }
      } else {
         if (!getLeft(node).isEmpty()) {
            this.root = getLeft(this.root);

         } else if (getRight(node) != null) {
            this.root = getRight(this.root);
         }
         this.root.setParent(null);

      }
   }

   private void removeTwoChildren(BSTNode<T> node) {
      BSTNode<T> sucessor = sucessor(node.getData());
      T suc = sucessor.getData();
      remove(sucessor.getData());
      node.setData(suc);
   }

   private boolean hasOneChild(BSTNode<T> node) {
      return ((node.getRight().isEmpty() || node.getLeft().isEmpty()) && node.getData() != null);
   }

   @Override
   public T[] preOrder() {
      int size = size();
      T[] arraySaida = (T[]) new Comparable[size];
      preOrder(this.root, arraySaida, 0);
      return arraySaida;
   }

   private int preOrder(BSTNode<T> node, T[] arraySaida, int index) {
      if (!node.isEmpty()) {
         arraySaida[index++] = node.getData();
         index = preOrder(getLeft(node), arraySaida, index);
         index = preOrder(getRight(node), arraySaida, index);
      }
      return index;
   }

   @Override
   public T[] order() {
      int size = size();
      T[] arraySaida = (T[]) new Comparable[size];
      order(this.root, arraySaida, 0);
      return arraySaida;
   }

   private int order(BSTNode<T> node, T[] arraySaida, int index) {
      if (!node.isEmpty()) {
         index = order(getLeft(node), arraySaida, index);
         arraySaida[index++] = node.getData();
         index = order(getRight(node), arraySaida, index);
      }
      return index;
   }

   @Override
   public T[] postOrder() {
      int size = size();
      T[] arraySaida = (T[]) new Comparable[size];
      postOrder(this.root, arraySaida, 0);
      return arraySaida;
   }

   private int postOrder(BSTNode<T> node, T[] arraySaida, int index) {
      if (!node.isEmpty()) {
         index = postOrder(getLeft(node), arraySaida, index);
         index = postOrder(getRight(node), arraySaida, index);
         arraySaida[index++] = node.getData();
      }
      return index;
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size() {
      return size(root);
   }

   private int size(BSTNode<T> node) {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty()) { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

   private BSTNode<T> getRight(BTNode<T> node) {
      return (BSTNode<T>) node.getRight();
   }

   private BSTNode<T> getLeft(BTNode<T> node) {
      return (BSTNode<T>) node.getLeft();
   }

   private BSTNode<T> getParent(BTNode<T> node) {
      return (BSTNode<T>) node.getParent();
   }
}
