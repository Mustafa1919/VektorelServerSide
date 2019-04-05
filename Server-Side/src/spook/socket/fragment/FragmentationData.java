package spook.socket.fragment;

import spook.database.iservice.IUserService;
import spook.database.service.UserService;
import spook.staticNumber.PriorityCode;

public class FragmentationData {

	private IUserService userService;
	private FragmentationSqlCommend fragmentationSqlCommend;
	
	public FragmentationData() {
		fragmentationSqlCommend = new FragmentationSqlCommend();
		this.userService = new UserService();
	}
	
	public boolean fragmentData(String data) {
		String priority = data.substring(0, 3);
		System.out.println(priority);
		boolean tempStatement = false;
		if(priority.equals(PriorityCode.LogOut))
			tempStatement = false;
		
		else if(priority.equals(PriorityCode.LogOn)) {
			//Kayit olma servisi cagir
			try {
				System.out.println(data.substring(3, data.length()));
				tempStatement = userService.save(data.substring(3, data.length()));
			} catch (Exception e) {
				System.out.println("FragmentationData/FragmentData kayıt olma servisinde hata oldu");
			}
		}
		
		else if(priority.equals(PriorityCode.LogIn)) {
			//giriş yapma servisini cagir
			System.out.println("login e geldi...");
			try {
				System.out.println(data.substring(3));
				tempStatement = userService.getUser(data.substring(3, data.length()));
			} catch (Exception e) {
				System.out.println("FragmentationData/FragmentData giris yapma servisinde hata oldu");
			}
		}
		
		else if(priority.equals(PriorityCode.sqlCommend)) {
			//sql komutu calistir.
			try {
				fragmentationSqlCommend.fragmentSql(data.substring(3));
			} catch (Exception e) {
				System.out.println("FragmentationData/FragmentData sql komut servisinde hata oldu");
			}
			tempStatement = true;
		}
		
		else {
			System.out.println("FragmentationData/FragmentData icinde hata olustu... ");
			tempStatement = false;
		}
		
		return tempStatement;
	}
	
}	
