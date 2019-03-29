package spook.socket.arrival;

import java.io.DataOutputStream;
import java.io.IOException;

import spook.connectionPool.ConnectionPool;

public class SenderSocket extends Thread{
	
	private DataOutputStream outputStream;
	private ConnectionPool connectionPool;
	private String ip ;
	private String sender;
	
	public SenderSocket(String ip , String sender) {
		this.ip = ip;
		this.sender = sender;
		connectionPool = ConnectionPool.getConnectionPool();
	}
	
	@Override
	public void run() {
		for(int i=0 ; i<=connectionPool.getSize(); i++) {
			if(connectionPool.getUser(i).getSocket().getInetAddress().getHostAddress().equals(ip)) {
				try {
					outputStream = new DataOutputStream(connectionPool.getUser(i).getSocket().getOutputStream());
					outputStream.writeUTF(sender);
				} catch (IOException e) {
					System.out.println("SenderSocket/run veri gondermede sorun oldu...");
				}
				finally {
					try {
						outputStream.close();
					} catch (IOException e) {
						System.out.println("SenderSocket/run output kapatilamadi ...");
					}
				}
			}
		}
	}
	
}
