package spook.socket.arrival;

import java.io.IOException;

import spook.connectionPool.ConnectionPool;

public class SenderSocket extends Thread {

	private ConnectionPool connectionPool;
	private String ip;
	private String sender;

	public SenderSocket(String ip, String sender) {
		this.ip = ip;
		this.sender = sender;
		connectionPool = ConnectionPool.getConnectionPool();
	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		for (int i = 0; i <= connectionPool.getSize(); i++) {
			try {
				System.out.println(connectionPool.getUser(i).getSocket().getInetAddress().getLocalHost().getHostAddress());
				if (connectionPool.getUser(i).getSocket().getInetAddress().getLocalHost().getHostAddress().equals(ip)) {
					connectionPool.getUser(i).getOutputStream().writeUTF(sender);
					System.out.println("mesaj göndermeye gönderdi...");
				}
			} catch (IOException e) {
				System.out.println("SenderSocket/run veri gondermede sorun oldu...");
			}

		}
	}
}
