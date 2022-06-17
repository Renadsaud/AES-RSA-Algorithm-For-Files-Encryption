/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_is;

import java.io.File;
import java.io.IOException;
import java.net.Socket;


public class Client {
    
    private Socket socket;
    
    public void startClient() throws IOException{
        
        socket = new Socket("192.168.1.155",5555);
        
    }
    
    
    public static void main(String[] args) throws IOException {
        
        File file = new File("C:\\Users\\razan\\Desktop\\PROJECT IS\\_cipher.enc");//------------FILE AND PATH-------------->
        
        Client client = new Client();
        client.startClient();
        
        SendReciveController fileController = new SendReciveController();
        
        fileController.sendFile(file, client.socket);
        fileController.reciveFile(client.socket);
        
        
    }
    
}
