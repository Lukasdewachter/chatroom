import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        String hostName = "localhost";
        int portNumber = 4444;
        Socket socket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        final Scanner sc =new Scanner(System.in);
        Thread sender= new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("username: ");
                String username= sc.nextLine();
                String userInput;
                while (true) {
                    userInput=sc.nextLine();
                    if(userInput == "exit"){
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                    out.println(username+": "+ userInput);
                    }
                }
            }
        });
        Thread receiver=new Thread(new Runnable() {
            String msg;
            public void run() {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (true){
                        msg=in.readLine();
                        System.out.println(msg);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        sender.start();
        receiver.start();
        sc.close();
    }
}
