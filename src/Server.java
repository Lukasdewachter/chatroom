import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws IOException {
        ArrayList <PrintWriter> outlist =new ArrayList<PrintWriter>();
        int portNumber = 4444;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)){
            while(true){
                new ServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}