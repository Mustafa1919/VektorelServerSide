package spook.database.iservice;

import java.util.List;

import spook.database.dao.UserDao;
import spook.database.idao.IUserDao;
import spook.model.User;

public interface IUserService {
	
	public IUserDao userDao = new UserDao();
	
	public boolean save(String data) throws Exception;
	
	public boolean update(String data) throws Exception;
	
	public boolean delete(String data) throws Exception;
	
	public String getFriend(String data) throws Exception;
	
	public List<User> getAllUsers() throws Exception;
	
	public boolean getUser(String data) throws Exception;
	
	public boolean logOutUser(String ip) throws Exception;
	
	public String getUserName(String ip) throws Exception;

}
