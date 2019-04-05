package spook.connectionPool;

import java.util.ArrayList;
import java.util.List;

public class UserPool {

	private static UserPool userPool = null;
	private List<String> sqlCommend = null;
	private List<String> userName = null;
	
	private UserPool() {
		if (sqlCommend == null)
			sqlCommend = new ArrayList<String>();
		if(userName == null)
			userName = new ArrayList<String>();
	}
	
	public static UserPool getUserPool() {
		if(userPool == null)
			userPool = new UserPool();
		return userPool;
	}
	
	public boolean addCommend(String sql, int loc) {
		sqlCommend.add(loc, sql);
		return true;
	}
	public boolean addUserName(String username, int loc) {
		userName.add(loc, username);
		return true;
	}
	
	public int getSize() {
		return sqlCommend.size();
	}
	
	public boolean deleteSql(int loc) {
		sqlCommend.remove(loc);
		userName.remove(loc);
		return true;
	}
	
	public int ifThereIs(String username) {
		int loc = 0;
		for(String s : userName) {
			if(s.equals(username))
				return loc;
			loc++;
		}
		return -1;
	}
	
	public String getUsername(int loc) {
		return userName.get(loc);
	}
	
	public String getData(int loc) {
		return sqlCommend.get(loc);
	}
	
}
