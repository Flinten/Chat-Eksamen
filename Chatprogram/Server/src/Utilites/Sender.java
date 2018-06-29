package Utilites;

/**
 * Created by Ejer on 04-06-2018.
 */
public class Sender {
    public static void sendMessageToAll(Client clientSender,String message){
        Chatroom.getChatroomList().stream().forEach(client -> {
            if(client != clientSender){
                client.sendMessage(clientSender.getName()+" Send: "+message);
            }
        });

    }
}
