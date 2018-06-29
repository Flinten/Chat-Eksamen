import Utilites.ChatMessages;
import Utilites.Chatroom;
import Utilites.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Ejer on 03-06-2018.
 */
public class Main {

        public static final int PORT = 5555;
        public static boolean isRunning = false;
        public static String CHATROOM_NAME = "Chatroom";
        public static int ROOM_SIZE = 2;
        public static int MAX_ROOMS = 3;
        public static ArrayBlockingQueue ROOM_QUE = null;
        public static int ROOM_COUNT = 0;
        public static int JOINED_COUNT = 0;

        public static String[] hardCodeMessages;

    public static void main(String[] args) {
        //hardCode();
        generateRandomWords(100000);
        ChatMessages.addAll(hardCodeMessages);
        ServerSocket serverSocket = null;

        Chatroom chatroom1 = new Chatroom(CHATROOM_NAME+" "+ROOM_COUNT++,ROOM_SIZE);
        System.out.println("Server has startet on port "+PORT);
        System.out.println("Chatroom: "+CHATROOM_NAME+" "+ROOM_COUNT++ +" has just been createt\n");


        try {
            serverSocket = new ServerSocket(PORT);
            isRunning = true;

        } catch (IOException e) {
            System.out.println("Could not start Server on port "+PORT);
            e.printStackTrace();
        }

        while (isRunning){
            try {
                Socket socket = serverSocket.accept();
                JOINED_COUNT++;
                System.out.println("Client has joined "+socket.getInetAddress());

                chatroom1.addClient(new Client(JOINED_COUNT,"Client "+JOINED_COUNT,socket));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public static void hardCode(){
        hardCodeMessages = new String[]{"Hej","Davs","Hvordan går det?","Skattekisten er fyldt", "Hvem spiller fortnite?" +
                "Jeg gør","Hvad laver i?", "Grønsager samger godt", "Vuf Vuf", "Min hånd gør ondt","Danish","King anton" +
                "how you doing!!","Doing well here","brb","Bye","CS or Fortnite?","Jeg smutter i Fakta","Cykelhjelm",
        "Godaften","Hejsa!!!", "Casper kern", "LÆKKERT!"};
    }
    public static void generateRandomWords(int numberOfWords)
    {
        hardCodeMessages = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            hardCodeMessages[i] = new String(word);
        }
    }


}
