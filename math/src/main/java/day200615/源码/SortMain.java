package day200615.源码;

import java.util.Arrays;
import java.util.Random;

public class SortMain {

    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = random.nextInt(100);
        }
        System.out.println("原始数组:" + Arrays.toString(arr));
        System.out.println("选择排序：");
        selectSort(Arrays.copyOfRange(arr,0,arr.length));
        System.out.println("冒泡排序：");
        bubbleSort(Arrays.copyOfRange(arr,0,arr.length));
    }

    /**
     * 选择排序
     */
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("最终排序结果："+Arrays.toString(arr));
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr) {
        int temp = 0;
        for (int j = 0; j < arr.length - 1; j++) {
            for (int i = 0; i < arr.length - 1 - j; i++) {
                if (arr[i] > arr[i + 1]) {
                    // charrnge
                    temp = arr[i + 1];
                    arr[i + 1] = arr[i];
                    arr[i] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("最终排序结果："+Arrays.toString(arr));
    }
}
