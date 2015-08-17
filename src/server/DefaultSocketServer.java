package server;
//this class is used to connect the default Sockets
/**
 * Created by santa on 6/23/15.
 * server class to establish a connection with DefaultSocketClient ( client sockets)
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

import dbconnector.Operation;
import model.Automobile;

public class DefaultSocketServer extends Exception{

    private static ServerSocket serverSocket = null;
    AutoServer autoServerReadObject = new BuildCarModelOptions();
    ObjectInputStream ois = null;


    public boolean openConnection(){
        try {
            serverSocket = new ServerSocket(4444);
            System.out.println(" listening on port 4444 ");
        } catch (IOException e) {
            System.err.println(" can not listen to port 4444 ");
            return false;
        }
        return true;
    }

    public void handleSession() {

        int filesize = 2058386;
        int bytesRead;
        int currentTot = 0;

        try{
            Socket client = null;
             client = serverSocket.accept();
            byte [] bytearray = new byte [filesize];
            InputStream is = client.getInputStream();
                ois = new ObjectInputStream(is);
                 String option = (String)ois.readObject();

                System.out.println("Client has choose option to perform : " + option);
                if (option.equals("upload")) {

                    FileOutputStream fos = new FileOutputStream("a2.Properties");
                    BufferedOutputStream bos = new BufferedOutputStream(fos);
                    bytesRead = is.read(bytearray, 0, bytearray.length);
                    currentTot = bytesRead;
                    do {
                        bytesRead = is.read(bytearray, currentTot, (bytearray.length - currentTot));
                        if (bytesRead >= 0)
                            currentTot += bytesRead;

                    } while (bytesRead > -1);
                    bos.write(bytearray, 0, currentTot);
                    bos.flush();
                    bos.close();
                    System.out.println(" Bytes written " + currentTot);
                    System.out.println(" message is sent to the client " + "OK");
                    autoServerReadObject.buildusingProperty("a2.Properties");
                    
                }
                else if(option.equals("configure")){
                	
                    ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                    ArrayList<String> availableModels=autoServerReadObject.getModelList();                 
                    LinkedHashMap<String, Automobile> autoList = (LinkedHashMap<String, Automobile>) autoServerReadObject.getAutomobileList(); 
                    autoServerReadObject.sendAutomobileList(os, autoList);		                                  
                    //autoServerReadObject.sendSelectedAutomobile(os, strSelection);
                    
                } else {
                    System.out.println("invalid input from client....");
                }

            } catch (Exception e){
            System.out.println(".. ");
        }
    }

    public void closeSession(){
        try {
            serverSocket.close();
        }
        catch (IOException e){
            System.err.println(" error in closing the server socket  ");
        }
    }


    public void run(){
        openConnection();
        while(true) {
            handleSession();
        }

    }

    public static void main(String[] args) {

        DefaultSocketServer s1 = new DefaultSocketServer();

        s1.run();

    }

}
