package Utilites;

import java.io.*;
import java.net.Socket;

/**
 * Created by Ejer on 03-06-2018.
 */
public class Client implements Runnable{

    private int id;
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(int id, String name, Socket socket) throws IOException {
        this.id = id;
        this.name = name;
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        String message;

        try {
            while ((message = in.readLine())!= null){
                System.out.println(name+": "+message);
                //Denne metode bruger Mediator pattern som er den rigtige måde at gøre det på
                //MEDIATOR PATTERN
                Sender.sendMessageToAll(this,message);
                //Denne metode bruger observer pattern som er Anti case i dette system
                //OBSERVER PATTERN
                //Chatroom.sendMessageObserver(message);

                //Her bruger vi Singleton pattern til at tlføje meddelser til en liste
                ChatMessages.addMessage(message);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized void sendMessage(String message){
        out.println(message);
        out.flush();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
