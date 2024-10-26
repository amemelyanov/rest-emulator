package org.example.rest.exercise;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) {
        int n = getDimension();
        int[][] array = getRandomArray(n);
        printArray(array);

        int minElement = getMinSideDiag(array);
        System.out.printf("Минимальный элемент побочной диагонали равен - %s%n", minElement);
    }

    private static int getDimension() {
        int n;
        while (true) {
            System.out.println("Введите целое число больше 2");
            Scanner in = new Scanner(System.in);
            n = in.nextInt();
            if (n >= 3) {
                break;
            }
            System.out.println("Число некорректно, повторите ввод");
        }
        return n;
    }

    private static int[][] getRandomArray(int n) {
        Random random = new Random();
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = random.nextInt(0, 9);
            }
        }
        return array;
    }

    private static void printArray(int[][] array) {
        for (int[] element : array) {
            System.out.println(Arrays.toString(element));
        }
    }

    private static int getMinSideDiag(int[][] array) {
        int minElement = Integer.MAX_VALUE;
        for (int i = array.length - 1, j = 0; i >= 0; i--, j++) {
            minElement = Math.min(minElement, array[i][j]);
        }
        return minElement;
    }

}
