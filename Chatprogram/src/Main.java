import Utilites.MessageSender;

import java.io.*;
import java.net.Socket;

public class Main {

    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 5555;

    public static void main(String[] args) {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            Socket socket = new Socket(SERVER_HOSTNAME,SERVER_PORT);
            System.out.println("You have joined "+SERVER_HOSTNAME+": "+SERVER_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.out.println("Could not join: "+SERVER_HOSTNAME+": " +SERVER_PORT);
            e.printStackTrace();
        }
        MessageSender messageSender = new MessageSender(out);
        Thread thread = new Thread(messageSender);
        thread.start();

        String message;
        try {
            //printer kun hvis der er besked
            while ((message=in.readLine())!= null){
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
