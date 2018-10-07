import java.io.*;
import java.net.*;
 
public class EchoClient {
    public static void main(String[] args) throws Exception {
 	
        if (args.length != 1) {
            System.err.println("Usage: java EchoClient <host name>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = 44288;
 
        try (
            Socket echoSock = new Socket(hostName, portNumber);
            PrintWriter prOut = new PrintWriter(echoSock.getOutputStream(), true);
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(echoSock.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
	    String exit = ".";
            while ((userInput = stdIn.readLine()) != null) {
                prOut.println(userInput);
			if(userInput.equals(exit)){
				System.exit(1);
			}
                System.out.println("Server: " + bufIn.readLine());
            }
        } catch (Exception e) {
            System.err.println("Something went wrong");
			System.out.println(e.getMessage());
        }
    }
}
