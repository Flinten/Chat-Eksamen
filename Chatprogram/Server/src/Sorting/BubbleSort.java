package Sorting;

import java.util.List;

/**
 * Created by Ejer on 04-06-2018.
 */
public class BubbleSort {



    public static void bubbleSort(String[] arr){

        for (int j = 0; j < arr.length; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i].compareTo(arr[j]) < 0) {
                    String t = arr[j];
                    arr[j] = arr[i];
                    arr[i] = t;
                }
            }

        }

    }
}
