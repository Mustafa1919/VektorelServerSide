package spook.connectionPool;

import java.util.ArrayList;
import java.util.List;

import spook.model.User;

public class UserPool {

	private static UserPool userPool = null;
	private List<User> listUser = null;
	
	private UserPool() {
		if (listUser == null)
			listUser = new ArrayList<User>();
	}
	
	public static UserPool getUserPool() {
		if(userPool == null)
			userPool = new UserPool();
		return userPool;
	}
	
	public void addUser(User user) {
		listUser.add(user);
	}
	
	public boolean ifIsThisUser(User user) {
		for (User usr : listUser) {
			if(usr.equals(user))
				return true;
		}
		return false;
	}
	
	public void deleteUser(User user) {
		listUser.remove(user);
	}
	
	public boolean ifPoolFull() {
		if(listUser.size() <= 100)
			return true;
		else
			return false;
	}
	
}
