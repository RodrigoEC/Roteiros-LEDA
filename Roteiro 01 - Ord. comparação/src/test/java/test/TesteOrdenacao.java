package test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sorting.AbstractSorting;
import sorting.variationsOfBubblesort.SimultaneousBubblesort;

import java.util.Arrays;
import java.util.Random;

public class TesteOrdenacao {

    private Integer[] arr;
    private AbstractSorting<Integer> sorting;
    private Random random;

    @Before
    public void setUp(){
        sorting = new SimultaneousBubblesort<>();
        random = new Random();
        arr = new Integer[5000 + random.nextInt(50000)];
    }

    private void preencheArray(){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(25000);
        }
    }

    @Test
    public void testSort() {
        preencheArray();
        Integer[] copyArr = Arrays.copyOf(arr, arr.length);
        sorting.sort(arr, 0, arr.length - 1);
        Arrays.sort(copyArr, 0, copyArr.length);
        Assert.assertArrayEquals(copyArr, arr);
    }

}
