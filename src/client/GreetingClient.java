package client;

//File Name GreetingClient.java
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import pi.GreetingServer;

public class GreetingClient
{

	public static void main(String[] args)
	{
		// String serverName = "retropie";
		String serverName = "127.0.0.1";
		int port = GreetingServer.port;
		if (args.length > 0)
			serverName = args[0];
		if (args.length > 1)
			port = Integer.parseInt(args[1]);
		try
		{
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);

			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			byte speed = 0;
			byte puissance = 25;

			byte[] message = new byte[] { speed, puissance };
			out.writeInt(message.length);
			out.write(message);
			// out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);

			System.out.println("Server says " + in.readUTF());
			client.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}