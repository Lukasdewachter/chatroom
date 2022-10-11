import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ArrayList <PrintWriter> outlist =new ArrayList<PrintWriter>();
        int portNumber = 4444;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
            while(true){
                Socket socket = serverSocket.accept();
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                outlist.add(out);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));

                new ServerThread(in,outlist).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}