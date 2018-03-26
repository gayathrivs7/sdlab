import java.io.*;
import java.net.*;
public class Q1ServerThread implements Runnable
{
ServerSocket serverSocket = null;
Socket socket = null;
BufferedReader readFromClient = null; BufferedReader readFromServer = null; PrintWriter
writeToClient = null;
String message = null;
public Q1ServerThread()
{
try {
serverSocket = new ServerSocket(4444); System.out.println("Waiting for connection...");
socket = serverSocket.accept(); System.out.println("Connection accepted...");
readFromClient = new BufferedReader(new InputStreamReader( socket.getInputStream()));
writeToClient = new PrintWriter( socket.getOutputStream(), true);
readFromServer = new BufferedReader(new InputStreamReader( System.in));
new Thread(this).start();
while(true)
{
message = readFromServer.readLine(); writeToClient.println(message); writeToClient.flush();
if(message.equalsIgnoreCase("exit"))
{ System.exit(1);}
}
}
catch(IOException exp) { exp.printStackTrace();}
}
public void run()
{
try {
while(true) {
String msg = readFromClient.readLine();
if(!msg.equalsIgnoreCase("exit"))
{
System.out.println(msg);
}
else
{
System.exit(1);
}
}
}
catch(Exception exp) {
exp.printStackTrace();
}
}
}
