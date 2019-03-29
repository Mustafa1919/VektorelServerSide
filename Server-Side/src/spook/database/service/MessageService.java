package spook.database.service;

import java.util.Date;

import spook.database.iservice.IMessageService;
import spook.model.Message;
import spook.socket.arrival.SenderSocket;
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
			message.setReceiverIp(userDao.userGetIp(tempData[1]));
			if(messageDao.kaydet(message)) {
				new SenderSocket(message.getReceiverIp(), message.getMessage());
				return true;
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
			message.setReceiverIp(userDao.userGetIp(tempData[1]));
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
			message.setReceiverIp(userDao.userGetIp(tempData[1]));
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

}
