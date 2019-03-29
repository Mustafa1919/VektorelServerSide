package spook.database.service;

import java.io.DataOutputStream;
import java.util.List;

import spook.database.iservice.IUserService;
import spook.model.User;
import spook.socket.arrival.SenderSocket;
import spook.util.CharacterSeparator;

public class UserService implements IUserService {
		
	@Override
	public boolean save(String data) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		String[] tempUser = data.split(CharacterSeparator.Separator);
		if (tempUser.length == 4) {
			user.setUserName(tempUser[0]);
			user.setPaswd(tempUser[1]);
			user.setProfileName(tempUser[2]);
			user.setConnectionIp(tempUser[3]);
			if (userDao.kaydet(user))
				return true;
			else {
				System.out.println("UserService/save hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			System.out.println("UserService/save hata olustu data 4 elemana esit degil");
			return false;
		}

	}

	@Override
	public boolean update(String data) throws Exception {
		User user = new User();
		String[] tempUser = data.split(CharacterSeparator.Separator);
		if (tempUser.length == 4) {
			user.setUserName(tempUser[0]);
			user.setPaswd(tempUser[1]);
			user.setProfileName(tempUser[2]);
			user.setConnectionIp(tempUser[3]);
			if (userDao.guncelle(user))
				return true;
			else {
				System.out.println("UserService/update hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			System.out.println("UserService/update hata olustu data 4 elemana esit degil");
			return false;
		}
	}

	@Override
	public boolean delete(String data) throws Exception {
		User user = new User();
		String[] tempUser = data.split(CharacterSeparator.Separator);
		if (tempUser.length == 4) {
			user.setUserName(tempUser[0]);
			user.setPaswd(tempUser[1]);
			user.setProfileName(tempUser[2]);
			user.setConnectionIp(tempUser[3]);
			if (userDao.sil(user))
				return true;
			else {
				System.out.println("UserService/delete hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			System.out.println("UserService/delete hata olustu data 4 elemana esit degil");
			return false;
		}
	}

	@Override
	public String getFriend(String data) throws Exception {
		// TODO Auto-generated method stub
		List<User> listUser = userDao.listele(new User());
		String userNames = "";
		for(User user : listUser) {
			userNames += (user.getUserName() + CharacterSeparator.Separator);
		}
		//new SenderSocket(user.getConnectionIp(), userNames);
		return userNames;
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// giris yapanin sifresi kontrol edilcek varsa true yoksa false döner
	@Override
	public boolean getUser(String data) throws Exception {
		// TODO Auto-generated method stub
		String[] tempUser = data.split(CharacterSeparator.Separator);
		List<User> tempUserList = userDao.listele(new User());
		if (!tempUserList.isEmpty()) {
			for (User user : tempUserList) {
				if(user.getUserName().equals(tempUser[0]) && user.getPaswd().equals(tempUser[1]))
					user.setIsConnect(true);
					userDao.guncelle(user);
					new SenderSocket(user.getConnectionIp(), "");
					return true;
			}
			return false;
		}
		else {
			System.out.println("UserService/getUser liste bos dondu");
			return true;
		}
	}

}
