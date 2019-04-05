package client;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Client
{

    String ans, num1, num2 = "";

    public void sendToServer() throws IOException
    {
        Socket soc = new Socket("Localhost", 8090);

        DataInputStream ClientIn = new DataInputStream(soc.getInputStream());
        DataOutputStream ClientOut = new DataOutputStream(soc.getOutputStream());

        ClientOut.writeUTF(num1);
        ClientOut.writeUTF(num2);

        System.out.println(ClientIn.readUTF());
    }

    public void captureDetails() throws IOException
    {
        ans = JOptionPane.showInputDialog("Please enter either 1 or 2");

        if (ans.equalsIgnoreCase("1"))
        {
            num1 = JOptionPane.showInputDialog("Please enter the min number for the range");
            num2 = JOptionPane.showInputDialog("Please enter the max number for the range");
        }
        if (ans.equalsIgnoreCase("2"))
        {
            num1 = JOptionPane.showInputDialog("What size machine did you rent? Large, Medium or Small?");
            num2 = JOptionPane.showInputDialog("How many hours");
        }

        sendToServer();
    }

    public static void main(String[] args) throws IOException
    {
        new Client().captureDetails();
    }

}
