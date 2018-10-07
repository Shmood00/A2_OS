/* Ian Gerics and Adam Iannaci*/

import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws Exception {
         
        if (args.length != 1) { //If the expected argument is not given, exit and print usage requirements
            System.err.println("Usage: java EchoClient <host name>");
            System.exit(1);
        }
 
        String hostName = args[0];
        int portNumber = 44288;
 
        try ( // Try to create socket and buffers
            Socket echoSock = new Socket(hostName, portNumber);
            PrintWriter prOut = new PrintWriter(echoSock.getOutputStream(), true);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(echoSock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
			String exit = ".";
            while ((userInput = stdIn.readLine()) != null) { // Read line and send to echo server 
                prOut.println(userInput);
				if(userInput.equals(exit)){
					System.exit(1); // Exit when user enters character . 
				}
                System.out.println("Server: " + bufIn.readLine()); // Print reply
            }
        } catch (Exception e) { // Throw exception if something goes wrong
            System.err.println("Something went wrong");
			System.out.println(e.getMessage());
        }
    }
}
