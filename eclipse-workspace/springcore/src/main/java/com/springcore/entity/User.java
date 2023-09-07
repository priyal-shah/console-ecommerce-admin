package com.springcore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int type;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private long mob;
	private int age;
	private String gender;
	private Date date_created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMob() {
		return mob;
	}
	public void setMob(long mob) {
		this.mob = mob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created() {
		this.date_created = new Date();
	}
	/**
	 * @param name
	 * @param email
	 * @param password
	 * @param date_created
	 */
	public User(String name, String email, String password, int type) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.date_created = new Date();
		this.type=type;
	}
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [user_id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", mob="
				+ mob + ", age=" + age + ", gender=" + gender + ", date_created="
				+ date_created + "]";
	}
	
	
	
}
