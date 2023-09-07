package com.springcore;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.Dao.UserDao;
import com.springcore.entity.User;

public class LoginPage {

	public static void getStated() {
		Scanner sc = new Scanner(System.in);
		ApplicationContext cn=new ClassPathXmlApplicationContext("config.xml");
		boolean exit=false;
		while (!exit) {
		System.out.println(
				"if a new user please register else login\n \n to register enter 1"
				+ "\n to login as admin enter 2 "
				+ "\n to login as custmor enter 3 "
				+ "\n to exit the application enter 0");
		int operationType = sc.nextInt();
		
			switch (operationType) {
			case 0:{
				exit=true;
				break;
			}
			case 1: {
				System.out.println("enter your first name");
				
				String first_name=sc.next();
				System.out.println("enter your last name");
				String lst_name=sc.next();
				String user_name = first_name+" "+lst_name;

				System.out.println("enter your email id (email should not contain space)");
				String user_email = sc.next();

				System.out.println("enter your password (password should not contain space)");
				String user_password = sc.next();
				
				System.out.println(
						"if you want to be admin enter 1 else enter 0\n for admin later you will need some bussiness proofs");
				int user_type = sc.nextInt();
				
				User user = new User(user_name, user_email, user_password, user_type);
				
				UserDao userDao = (UserDao)cn.getBean("userDao");

				boolean result = userDao.registerUser(user);

				if (result)
					System.out.println("<--------You have registered SUCESSFULLY--------->");
				else
					System.out.println("<------you could not register...went into some ERROR------>");
				
				break;
			}
			case 2:{
				UserDao userDao = (UserDao)cn.getBean("userDao");
				System.out.println("enter your email id ");
				String user_email = sc.next();

				System.out.println("enter your password");
				String user_password = sc.next();
				
				User login_user=userDao.login(user_email, user_password,1);
				if(login_user==null) System.out.println("<ERROR------Wrong user id or password");
				else if(login_user!=null) AdminPage.homeForAdmin(login_user);
				break;
			}
			case 3: {
				UserDao userDao = (UserDao)cn.getBean("userDao");
				System.out.println("enter your email id ");
				String user_email = sc.next();

				System.out.println("enter your password");
				String user_password = sc.next();
				
				User login_user=userDao.login(user_email, user_password,0);
				if(login_user==null) System.out.println("<ERROR------Wrong user id or password");
				else if(login_user!=null) System.out.println("\n<======hello "+login_user.getName()+"=========>\n");
				break;
			}
			default:
				System.out.println("please enter correct operation");
				
			}
		}
		
		if(exit) System.out.println("Thank You for visiting us");
	}
}
