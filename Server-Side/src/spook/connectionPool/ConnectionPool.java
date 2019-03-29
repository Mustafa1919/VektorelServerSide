package spook.connectionPool;

import java.util.ArrayList;
import java.util.List;

import spook.socket.arrival.ListeningSocket;
import spook.staticNumber.UserNumber;

public class ConnectionPool {
	
	private static ConnectionPool pool = null;
	private List<ListeningSocket> list ;
	
	
	private ConnectionPool() {
		list = new ArrayList<ListeningSocket>();
	}
	
	public static ConnectionPool getConnectionPool() {
		if(pool == null)
			pool = new ConnectionPool();
		return pool;
	}
	
	public boolean addUser(ListeningSocket listeningSocket) {
		if(list.size() < UserNumber.userNumber) {
			list.add(listeningSocket);
			return true;
		}
		System.out.println("Pool Dolu");
		return false;
		
	}
	
	public boolean deleteUser(ListeningSocket listeningSocket) {
		return list.remove(listeningSocket);
	}
	
	public ListeningSocket getUser(int size) {
		return list.get(size);
	}
	
	public int getSize() {
		return list.size()-1;
	}
	
}
