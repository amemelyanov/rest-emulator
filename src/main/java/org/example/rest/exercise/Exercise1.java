package org.example.rest.exercise;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Класс содержит методы по поиску минимального элемента на побочной диагонали
 * матрицы, пользователь через консоль задает размерность матрицы, которая создается
 * и наполняется случайными целыми числами.
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
public class Exercise1 {

    /**
     * Метод выполняет запуск приложения, выполняет методы класса и
     * выводит результат вычислений на консоль.
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        int n = getDimension();
        int[][] array = getRandomArray(n);
        printArray(array);

        int minElement = getMinSideDiag(array);
        System.out.printf("Минимальный элемент побочной диагонали равен - %s%n", minElement);
    }

    /**
     * Метод запрашивает и получает пользовательский
     * ввод с информацией о размерности массива.
     *
     * @return int размерность массива
     */
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

    /**
     * Метод создает массива и наполняет
     * его случайными числами.
     *
     * @param n размерность массива
     * @return array квадратный массив случайных целых чисел
     */
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

    /**
     * Метод выводит массив на экран.
     *
     * @param array массива
     */
    private static void printArray(int[][] array) {
        for (int[] element : array) {
            System.out.println(Arrays.toString(element));
        }
    }

    /**
     * Метод находит минимальный элемент на побочной
     * диагонали массива и возвращает его.
     *
     * @param array массив, в котором ищется минимальный элемент
     * @return int минимальный элемент побочной диагонали
     */
    private static int getMinSideDiag(int[][] array) {
        int minElement = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            minElement = Math.min(minElement, array[array.length - 1 - i][i]);
        }
        return minElement;
    }

}
