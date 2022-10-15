import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private ArrayList<PrintWriter> outlist;
    public ServerThread(Socket socket) {
        super("ServerThread");
        this.socket = socket;
        outlist = new ArrayList<PrintWriter>();
    }

    public void run() {
            try(
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ){
                outlist.add(out);
                String inputLine;
                while (true) {
                    try {
                        if (((inputLine = in.readLine()) != null));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    for (PrintWriter outM:outlist) {
                        outM.println(inputLine);
                    }
                    if(inputLine != null) {
                        System.out.println(inputLine);
                        String[] temp = inputLine.split(" ");
                        if(temp[1].equals("exit"))break;
                    }

                }socket.close();
            }catch(IOException e){
                e.printStackTrace();
        }
    }
}
