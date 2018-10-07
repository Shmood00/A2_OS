import java.net.*;
import java.io.*;
 
public class EchoServer {
    public static void main(String[] args) throws Exception {

        int portNumber = 44288;
         
        try (
            ServerSocket srvSock = new ServerSocket(portNumber); 
            Socket cliSock = srvSock.accept();     
            PrintWriter prOut = new PrintWriter(cliSock.getOutputStream(), true);                   
            BufferedReader bufIn = new BufferedReader(new InputStreamReader(cliSock.getInputStream()));
        ) {
            String inLine;
            while ((inLine = bufIn.readLine()) != null) {
                prOut.println(inLine);
            }
        } catch (Exception e) {
            System.out.println("Exception caught when trying to run");
            System.out.println(e.getMessage());
        }
    }
}
