package spook.database.dao;

import java.util.List;

import spook.database.idao.IUserDao;
import spook.jutil.DataBaseServis;
import spook.model.User;

public class UserDao extends DataBaseServis<User> implements IUserDao{

	@Override
	public String userGetIp(String userName) throws Exception{
		String ip = null;
		List<User> listUser = listele(new User());
		if(!listUser.isEmpty()) {
			for(User user : listUser) {
				if(user.getUserName().equals(userName))
					return user.getConnectionIp();
			}
		}
		return ip;
	}

}
