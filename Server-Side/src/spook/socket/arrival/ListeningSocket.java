package spook.socket.arrival;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import spook.connectionPool.ConnectionPool;
import spook.connectionPool.UserPool;
import spook.database.iservice.IMessageService;
import spook.database.iservice.IUserService;
import spook.database.service.MessageService;
import spook.database.service.UserService;
import spook.model.User;
import spook.socket.fragment.FragmentationData;
import spook.staticNumber.PriorityCode;

/*
 * ListeningSocket sinifi clientlerden gelen datalari dinlemek icin kullanilan siniftir
 * Gelen datalar dinlenerek datalari acmak icin olusturulan sinifa referansla aktarim yapar
 * Bu class sadece kayit olma istegi ve giris yapma isteklerini dinler 
 * Ve gerekli sinifa aktarim saglar
 * Sinif sadece dinleme gerceklestirir ve clientlere mesaj gondermez.
 */
public class ListeningSocket extends Thread {

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private ConnectionPool connectionPool;
	private boolean statement;
	private FragmentationData fragmentationData;
	private IUserService userService;
	private IMessageService messageService;

	public ListeningSocket(Socket socket) throws IOException, SocketException {
		this.statement = true;
		this.socket = socket;
		this.userService = new UserService();
		this.messageService = new MessageService();
		connectionPool = ConnectionPool.getConnectionPool();
		this.fragmentationData = new FragmentationData();
		ifSendMessage();
	}

	@Override
	public void run() {
		String data;
		try {
			while (statement) {
				inputStream = new DataInputStream(socket.getInputStream());
				outputStream = new DataOutputStream(socket.getOutputStream());
				while (statement) {
					data = inputStream.readUTF();
					System.out.println("Thread : " + getName() + "   " + data);
					// parcalama islemleri yapilan sinifa aktarim yapilacak
					statement = fragmentationData.fragmentData(data);
				}
			}

		} catch (IOException e) {
			// Loglama yap
		}
		try {
			System.out.println("Thread : " + getName() + "  sonlandı...");
			this.socket.close();
			this.inputStream.close();
			this.outputStream.close();
			userService.logOutUser(socket.getInetAddress().getLocalHost().getHostAddress());
			connectionPool.deleteUser(ListeningSocket.this);
		} catch (IOException e) {
			System.out.println("Listening socket kapatma islemlerinde hata olustu...");
		}
		catch (Exception e) {
			System.out.println("isConnect degistirilemedi...");
		}
	}

	public Socket getSocket() {
		return this.socket;
	}
	
	public DataOutputStream getOutputStream() {
		return this.outputStream;
	}
	
	public void ifSendMessage() {
		UserPool pool = UserPool.getUserPool();
		int loc;
		try {
			while((loc = pool.ifThereIs(userService.getUserName(socket.getInetAddress().getLocalHost().getHostAddress()))) != -1) {
				messageService.lateMessage(pool.getUsername(loc), pool.getData(loc));
				pool.deleteSql(loc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
