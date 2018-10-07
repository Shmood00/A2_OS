/* Ian Gerics and Adam Iannaci*/


import java.net.*;
import java.io.*;
 
public class EchoServer {
    public static void main(String[] args) throws Exception {
         
        if (args.length != 0) { // If the expected argument is not given, exit and print message
            System.err.println("Usage: java EchoServer");
            System.exit(1);
        }
         
        int portNumber = 44288;
         
        try ( // Try to create socket and buffers
            ServerSocket srvSock = new ServerSocket(portNumber); 
            Socket cliSock = srvSock.accept();     
            PrintWriter prOut = new PrintWriter(cliSock.getOutputStream(), true);                   
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(cliSock.getInputStream()));
        ) {
            String inLine;
            while ((inLine = bufIn.readLine()) != null) { // Read line from client
                prOut.println(inLine);
            }
        } catch (Exception e) { // Throw exception if something goes wrong
            System.out.println("Exception caught when trying to run");
            System.out.println(e.getMessage());
        }
    }
}
