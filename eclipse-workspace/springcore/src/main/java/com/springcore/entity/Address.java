package com.springcore.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private String id;
	private String user_id;
	private String houseNoOrName;
	private String street;
	private String city;
	private String state;
	private String country;
	private Long pin;
	private Date date_created;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getHouseNoOrName() {
		return houseNoOrName;
	}
	public void setHouseNoOrName(String houseNoOrName) {
		this.houseNoOrName = houseNoOrName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Long getPin() {
		return pin;
	}
	public void setPin(Long pin) {
		this.pin = pin;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	/**
	 * @param user_id
	 * @param houseNoOrName
	 * @param street
	 * @param city
	 * @param state
	 * @param country
	 * @param pin
	 */
	public Address(String user_id, String houseNoOrName, String street, String city, String state, String country,
			Long pin) {
		super();
		this.user_id = user_id;
		this.houseNoOrName = houseNoOrName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pin = pin;
		this.date_created=new Date();
	}
	/**
	 * 
	 */
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
