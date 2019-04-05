package spook.database.service;

import java.util.Date;
import java.util.List;

import spook.connectionPool.UserPool;
import spook.database.iservice.IMessageService;
import spook.model.Message;
import spook.model.User;
import spook.socket.arrival.SenderSocket;
import spook.staticNumber.PriorityCode;
import spook.util.CharacterSeparator;

public class MessageService implements IMessageService{
	
	@Override
	public boolean save(String data) throws Exception {
		String[] tempData = data.split(CharacterSeparator.Separator);
		Message message = new Message();
		if(tempData.length == 4) {
			message.setSenderUserName(tempData[0]);
			message.setReceiverUserName(tempData[1]);
			message.setMessage(tempData[2]);
			message.setSenderIp(tempData[3]);
			message.setSendDate(new Date());
			if(userDao.userGetIp(tempData[1]) != null) {
				message.setReceiverIp(userDao.userGetIp(tempData[1]));
			}
			else
				System.out.println("Ip == null geldi (mesaj servis save)");
			if(messageDao.kaydet(message)) {
				List<User> userList = userDao.listele(new User());
				for(User user : userList) {
					if(user.getUserName().equals(tempData[1])) {
						if(!user.getIsConnect()) {
							//listeye ekle
							UserPool pool = UserPool.getUserPool();
							int loc = pool.getSize();
							pool.addCommend(tempData[2], loc);
							pool.addUserName(tempData[1], loc);
							return true;
						}
						else {
							new SenderSocket(message.getReceiverIp(), message.getSenderUserName() + CharacterSeparator.Separator + message.getMessage()).run();
							return true;
						}
					}
				}
				return false;
			}
			else {
				System.out.println("MessageService/save kaydetme isleminde hata oldu");
				return false;
			}
		}
		else {
			System.out.println("MessageService/save data 4 elemandan farkli");
			return false;
		}
	}

	@Override
	public boolean update(String data) throws Exception {
		String[] tempData = data.split(CharacterSeparator.Separator);
		Message message = new Message();
		if(tempData.length == 4) {
			message.setSenderUserName(tempData[0]);
			message.setReceiverUserName(tempData[1]);
			message.setMessage(tempData[2]);
			message.setSenderIp(tempData[3]);
			message.setSendDate(new Date());
			if(userDao.userGetIp(tempData[1]) != null)
				message.setReceiverIp(userDao.userGetIp(tempData[1]));
			else
				System.out.println("Ip == null geldi (mesaj servis update)");
			if(messageDao.guncelle(message)) {
				new SenderSocket(message.getReceiverIp(), message.getMessage());
				return true;
			}
			else {
				System.out.println("MessageService/update kaydetme isleminde hata oldu");
				return false;
			}
		}
		else {
			System.out.println("MessageService/update data 4 elemandan farkli");
			return false;
		}
	}

	@Override
	public boolean delete(String data) throws Exception {
		String[] tempData = data.split(CharacterSeparator.Separator);
		Message message = new Message();
		if(tempData.length == 4) {
			message.setSenderUserName(tempData[0]);
			message.setReceiverUserName(tempData[1]);
			message.setMessage(tempData[2]);
			message.setSenderIp(tempData[3]);
			message.setSendDate(new Date());
			if(userDao.userGetIp(tempData[1]) != null)
				message.setReceiverIp(userDao.userGetIp(tempData[1]));
			else
				System.out.println("Ip == null geldi (mesaj servis delete)");
			if(messageDao.sil(message))
				return true;
			else {
				System.out.println("MessageService/delete kaydetme isleminde hata oldu");
				return false;
			}
		}
		else {
			System.out.println("MessageService/delete data 4 elemandan farkli");
			return false;
		}
	}

	@Override
	public boolean lateMessage(String userName, String data) throws Exception {
		String ip = userDao.userGetIp(userName);
		new SenderSocket(ip, userName + CharacterSeparator.Separator + data).run();
		return true;
	}

}
