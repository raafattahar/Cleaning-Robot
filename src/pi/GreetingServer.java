package pi;

//File Name GreetingServer.java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * You should't use anonymous ports (a.k.a. ephemeral ports) to implement a UDP
 * or TCP service. By default, these ports are in the range 32768 - 65535.
 * 
 * @author rafa
 *
 */
public class GreetingServer extends Thread
{
	private ServerSocket serverSocket;

	public static int port = 44044;// 32768 ;// 65535

	public GreetingServer(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(10000);
	}

	public void run()
	{
		while (true)
		{
			try
			{
				System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();

				System.out.println("Just connected to " + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());

				// System.out.println(in.readUTF());
				int length = in.readInt(); // read length of incoming message
				if (length > 0)
				{
					byte[] message = new byte[length];
					in.readFully(message, 0, message.length); // read the
					System.out.println(message[0] + " - " + message[1]);
				}
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();

			}
			catch (SocketTimeoutException s)
			{
				System.out.println("Socket timed out!");
				break;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String[] args)
	{
		if (args.length > 0)
			port = Integer.parseInt(args[0]);
		try
		{
			Thread t = new GreetingServer(port);
			t.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}