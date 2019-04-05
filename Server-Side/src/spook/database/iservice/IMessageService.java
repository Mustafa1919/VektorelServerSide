package spook.database.iservice;

import spook.database.dao.MessageDao;
import spook.database.dao.UserDao;
import spook.database.idao.IMessageDao;
import spook.database.idao.IUserDao;

public interface IMessageService {
	
	public IMessageDao messageDao = new MessageDao();
	public IUserDao userDao = new UserDao();
	
	public boolean save(String data) throws Exception;
	
	public boolean update(String data) throws Exception;
	
	public boolean delete(String data) throws Exception;
	
	public boolean lateMessage(String userName, String data) throws Exception;
	
}
