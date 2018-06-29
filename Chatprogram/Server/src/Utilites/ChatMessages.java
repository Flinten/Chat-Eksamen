package Utilites;

import Sorting.BubbleSort;

import java.util.*;

/**
 * Created by Ejer on 04-06-2018.
 */
public class ChatMessages {
    private static ArrayList<String> instance = new ArrayList<>();

    private ChatMessages() {
    }
    public static List getInstance(){
        return instance;
    }
    public static void addMessage(String message){
        instance.add(message);
    }
    public static void addAll(String[] list){
        Collections.addAll(instance, list);
    }
    public static void printAllMessages(){
        instance.stream().forEach(System.out::println);
    }
    public static void sortArrayListBubble(){
        long startTime;
        long endTime;

        String[] list = instance.stream().toArray(String[]::new);
        startTime = System.currentTimeMillis();
        BubbleSort.bubbleSort(list);
        endTime = System.currentTimeMillis();
        instance = new ArrayList<String>(Arrays.asList(list));
        System.out.println("BIG-O NOTATIONS: Bubble sort took "+(endTime - startTime)+
                " Milli seconds with "+instance.size()+" Chat messages");
    }
    //Primitive types som bliver soteret i Collections.sort bliver automatisk soteret med Quicksort
    //Objekter som soterers med Collections.sort bliver automatisk soteret med merge sort
    //Kilde: https://www.quora.com/What-sorting-algorithm-is-used-by-Javas-Collections-sort-and-why
    public static void sortArrayListQuick(){
        long startTime;
        long endTime;
        startTime = System.currentTimeMillis();
        Collections.sort(instance);
        endTime = System.currentTimeMillis();
        System.out.println("BIG-O NOTATIONS: Quick sort took "+(endTime - startTime)+
                " Milli seconds with "+instance.size()+" Chat messages");
    }

    public static String[] getArrayOfMessges(){
        String[] list = instance.stream().toArray(String[]::new);
        return list;
    }
}
