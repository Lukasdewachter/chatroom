import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket = null;
    private BufferedReader in;
    private ArrayList<PrintWriter> outList;
    public ServerThread(BufferedReader in, ArrayList<PrintWriter> outList) {
        this.in = in;
        this.outList= outList;
    }

    public void run() {
            String inputLine, outputLine;
            while (true) {
                try {
                    if (((inputLine = in.readLine()) != null));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (PrintWriter out:outList) {
                    out.println(inputLine);
                }
                System.out.println(inputLine);
            }



    }
}
