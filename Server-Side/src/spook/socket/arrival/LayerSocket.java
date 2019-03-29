package spook.socket.arrival;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import spook.connectionPool.ConnectionPool;

public class LayerSocket {

	private ServerSocket serverSocket;
	private ConnectionSocket connectionSocket;
	private ConnectionPool connectionPool;

	public LayerSocket() throws IOException {
		connectionPool = ConnectionPool.getConnectionPool();
		connectionSocket = ConnectionSocket.getConnectionSocket();
		serverSocket = connectionSocket.getServerSocket();
	}

	public void StartListening() {
		while (true) {
			try {
				if (serverSocket.isBound()) {
					Socket socket = serverSocket.accept();
					connectionPool.addUser(new ListeningSocket(socket));
					connectionPool.getUser(connectionPool.getSize()).start();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
