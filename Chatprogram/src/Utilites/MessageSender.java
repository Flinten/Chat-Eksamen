package Utilites;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Created by Ejer on 03-06-2018.
 */
public class MessageSender implements Runnable{

    private PrintWriter out;
    private boolean state = false;
    public MessageSender(PrintWriter out) {
        this.out = out;
        state = true;
    }
    @Override
    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (state) {
            try {
                String message = in.readLine();
                out.println(message);
                out.flush();
                System.out.println("Your Message: "+message);
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
