package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer 
{
	private static final int PORT = 5000;
	
	public static void main(String[] args) 
	{
		ServerSocket serverSocket = null;
		
		try 
		{
			// 1. 서버소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. Binding : Socket에 SocketAddress(IP Address + port) 바인딩한다
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localHostAddress = inetAddress.getHostAddress();
			
			serverSocket.bind(new InetSocketAddress(localHostAddress, PORT));
			System.out.println("[server] binding " + localHostAddress + ":" + PORT);
			
			// 3. Accept : 클라이언트로 부터 연결요청을 기다린다.
			Socket socket = serverSocket.accept(); //Blocking (밑으로안내려감)
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress(); // InetAddress 리턴 . ipAddress 리턴
			int remotePort = inetRemoteSocketAddress.getPort();
			System.out.println("[server] connected by client[" + remoteHostAddress + ":" + remotePort);
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
		{
				try 
				{
					if (serverSocket != null && !serverSocket.isClosed())
						serverSocket.close();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
