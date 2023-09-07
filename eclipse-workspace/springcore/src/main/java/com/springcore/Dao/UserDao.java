package com.springcore.Dao;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.ErrorType;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.springcore.entity.User;

public class UserDao {
	private HibernateTemplate hibernateTemplate;

	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@Transactional
	public boolean registerUser(User user) {
		if(hibernateTemplate==null) {
			System.out.println("error in connection with database");
			return false;
		}
		try {
			hibernateTemplate.save(user);
		}
		catch (Exception e) {
			// TODO: handle exception
			
			if(e.hashCode() ==1500020749) System.out.println("ERROR-----> email id should be unique <-------ERROR");
			else System.out.println(e.getMessage()+" hashcode: "+e.hashCode());
			return false;
		}
		
		return true;
	}
	
	
	public User login(String user_email, String user_password, int type) {
		User user= new User();
		user.setEmail(user_email);
		user.setPassword(user_password);
		user.setType(type);
		List<User> login_user=new ArrayList<>();
		try {
			 login_user=hibernateTemplate.findByExample(user);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
			return null;
		}
		
		if(login_user.size()==0) return null;
		if(login_user.get(0).getPassword().equals(user_password)) return login_user.get(0);
		else return null;
		}
	
}
