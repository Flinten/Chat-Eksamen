package Utilites;

import Sorting.BubbleSort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Ejer on 03-06-2018.
 */
public class Chatroom implements Runnable{

    private String name;
    private int roomSize;
    private static ArrayList<Client> chatroomList;
    private Queue messageQue;
    private boolean isRunning = false;

    public Chatroom(String name, int roomSize) {
        this.name = name;
        this.roomSize = roomSize;
        chatroomList = new ArrayList<>();
        messageQue =  new PriorityQueue();
        isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        printMenu();
        while (isRunning) {
            try {
                String message = in.readLine();
                commandType(message);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }

    public void commandType(String message){
        System.out.println();
        switch (message.toUpperCase()){
            //print all messages
            case "PA":{
                ChatMessages.printAllMessages();
                break;
            }
            //print all users
            case "PU":
                System.out.println("This is the chatusers inside the chatroom");
                chatroomList.stream().forEach(client -> {
                    System.out.println("Name: "+client.getName());
                });
                break;
            //print size
            case "PS":
                System.out.println("Room size is: "+chatroomList.size());
                break;
            //print boolean is ser running?
            case "PR":
                System.out.println("Is server running? : "+isRunning);
                break;
            //Soterer all
            case "SORTA":
                ChatMessages.sortArrayListBubble();
                break;
            //Quick sort
            case "SORTQ":
                ChatMessages.sortArrayListQuick();
                break;
            //Lukker server
            case "MENU":
                printMenu();
                break;
            case "EXIT":
                System.exit(1);


            default:{
                System.out.println("Server did not understand you Commando. Try again");
            }
        }
    }

    public synchronized void addClient(Client client){
        chatroomList.add(client);
    }
    public synchronized void removeClient(Client client){
        if(chatroomList.contains(client)){
            chatroomList.remove(client);
        }
    }
    //Observer Pattern
    //Anti case!!
    public static synchronized void sendMessageObserver(String message){
        for (Client client:chatroomList) {
            client.sendMessage(message);
        }
    }
    public void printMenu(){
        System.out.println("Menu\n");
        System.out.println("Type in a command and press enter\n");
        System.out.println("Press PA: Prints the whole chatlist");
        System.out.println("Press PU: Prints every online Chatter");
        System.out.println("Press PS: Prints how big the chat room is");
        System.out.println("Press PR: Prints the state of the server");
        System.out.println("Press SORTA: Sorts all the message that have been sent with BubbleSort");
        System.out.println("Press SORTQ: Sorts all the message that have been sent with QuickSort");
        System.out.println("Press MENU: Shows the menu");
        System.out.println("Press EXIT: Exits the Application");
    }

    public Chatroom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    public static ArrayList<Client> getChatroomList() {
        return chatroomList;
    }

    public void setChatroomList(ArrayList<Client> chatroomList) {
        this.chatroomList = chatroomList;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public Queue getMessageQue() {
        return messageQue;
    }

    public void setMessageQue(Queue messageQue) {
        this.messageQue = messageQue;
    }

    public Queue getQueue() {
        return messageQue;
    }

    public void setQueue(Queue queue) {
        this.messageQue = queue;
    }

    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {

        }
    }
}
