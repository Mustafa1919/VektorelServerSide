package spook.runner;

import java.util.Date;

import spook.jutil.DataBaseServis;
import spook.model.Message;
import spook.model.User;

public class Runner {
	public static void main(String[] args) {
		Message message = new Message("test", "test1", "asd", new Date(), "44444", "55555");
		User user = new User("Mustafa", "spook", "268414");
		DataBaseServis<Message> servis = new DataBaseServis<Message>();
		DataBaseServis<User> servis1 = new DataBaseServis<>();
		try {
			servis.kaydet(message);
			servis1.kaydet(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
