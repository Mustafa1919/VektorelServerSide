package spook.database.service;

import java.util.List;

import spook.database.iservice.IUserService;
import spook.model.User;
import spook.socket.arrival.SenderSocket;
import spook.staticNumber.PriorityCode;
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
			if (userDao.kaydet(user)) {
				new SenderSocket(user.getConnectionIp(), CharacterSeparator.Separator).run();
				return true;
			}
			else {
				new SenderSocket(tempUser[3], "f").run();
				System.out.println("UserService/save hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			new SenderSocket(tempUser[3], "f").start();
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
			if (userDao.guncelle(user)) {
				new SenderSocket(user.getConnectionIp(), CharacterSeparator.Separator).start();
				return true;
			}
			else {
				new SenderSocket(tempUser[3], "f").start();
				System.out.println("UserService/update hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			new SenderSocket(tempUser[3], "f").start();
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
			if (userDao.sil(user)) {
				new SenderSocket(user.getConnectionIp(), CharacterSeparator.Separator).start();
				return true;
			}
			else {
				new SenderSocket(tempUser[3], "f").start();
				System.out.println("UserService/delete hata olustu dao islemi hatali dondu");
				return false;
			}
		} else {
			new SenderSocket(tempUser[3], "f").start();
			System.out.println("UserService/delete hata olustu data 4 elemana esit degil");
			return false;
		}
	}
	
	//yeniden düzenle username olan kullanıcının arkadas listesini dön
	@Override
	public String getFriend(String userName) throws Exception {
		// TODO Auto-generated method stub
		List<User> listUser = userDao.listele(new User());
		String userNames = "";
		for(User user : listUser) {
			userNames += (user.getUserName() + CharacterSeparator.Separator);
		}
		//new SenderSocket(user.getConnectionIp(), userNames);
		return PriorityCode.ListReturn + userNames;
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
		System.out.println("String : tempUser[0] : " + tempUser[0]);
		System.out.println("String : tempUser[1] : " + tempUser[1]);
		if (!tempUserList.isEmpty()) {
			for (User user : tempUserList) {
				if(user.getUserName().equals(tempUser[0]) && user.getPaswd().equals(tempUser[1])) {
					System.out.println("User : " + user.getUserName());
					System.out.println("USer : " + user.getPaswd());
					user.setIsConnect(true);
					user.setConnectionIp(tempUser[2]);
					userDao.guncelle(user);
					new SenderSocket(user.getConnectionIp(), getFriend(user.getUserName())).start();
					return true;
				}
			}
			new SenderSocket(tempUser[2], getFriend(data)).start();
			return false;
		}
		else {
			new SenderSocket(tempUser[2], CharacterSeparator.Separator).start();
			System.out.println("UserService/getUser liste bos dondu");
			return true;
		}
	}

	@Override
	public boolean logOutUser(String ip) throws Exception {
		List<User> listUser = userDao.listele(new User());
		for(User user : listUser) {
			if(user.getConnectionIp().equals(ip)) {
				user.setIsConnect(false);
				userDao.guncelle(user);
				return true;
			}		
		}
		return false;
	}

	@Override
	public String getUserName(String ip) throws Exception {
		List<User> listUser = userDao.listele(new User());
		for(User user : listUser) {
			if(user.getConnectionIp().equals(ip)) {
				return user.getUserName();
			}		
		}
		return null;
	}

}
