package client;

import java.io.*;
import java.net.*;


public class Server
{
    public Server() throws IOException
    {
        ServerSocket servSoc = new ServerSocket(8090);
        Socket soc = servSoc.accept();
        
        Thread t1 = new Thread(new TWork(soc));
        t1.start();
    }   
    
    public static void main(String[] args) throws IOException
    {
        new Server();
    }
}
