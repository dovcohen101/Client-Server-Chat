package client;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class TWork implements Runnable
{

    Socket soc = new Socket("localhost", 8090);

    DataInputStream dbIn;
    DataOutputStream dbOut;

    public TWork(Socket soc) throws IOException
    {
        this.soc = soc;

        dbIn = new DataInputStream(soc.getInputStream());
        dbOut = new DataOutputStream(soc.getOutputStream());
    }

    public String calcCost(String size, String hours)
    {
        double tot = 0;

        if (size.equalsIgnoreCase("Small"))
        {
            int initial = 50;
            double hoursCost = Double.parseDouble(hours) * 30;
            tot = initial + hoursCost;
        }

        if (size.equalsIgnoreCase("Medium"))
        {
            double initial = 62.50;
            double hoursCost = Double.parseDouble(hours) * 36.25;
            tot = initial + hoursCost;
        }

        if (size.equalsIgnoreCase("Large"))
        {
            double initial = 40.50;
            double hoursCost = Double.parseDouble(hours) * 40.50;
            tot = initial + hoursCost;
        }

        return "The final cost is: " + tot;
    }

    public String selectRandomNumber(String min, String max)
    {
        int x = Integer.parseInt(max);
        int y = Integer.parseInt(min);

        int randomNum = ThreadLocalRandom.current().nextInt(y, x + 1);

        return "The random number is: " + randomNum;
    }

    @Override
    public void run()
    {
        try
        {
            String ans = dbIn.readUTF();
            String num = dbIn.readUTF();

            if (ans.equalsIgnoreCase("Small") || ans.equalsIgnoreCase("Medium") || ans.equalsIgnoreCase("Large"))
            {
                dbOut.writeUTF(calcCost(ans, num));
            }
            else
            {
                dbOut.writeUTF(selectRandomNumber(ans, num));
            }
        }
        catch (Exception e)
        {
        }
    }

}
