package com.springcore.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user_id;
	private Double amount;
	private String Status;
	private Date date_created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser_id() {
		return user_id;
	}
	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public void setAmount(String amount) {
		this.amount = Double.parseDouble(amount);
	}
	
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	
	/**
	 * @param user_id
	 * @param amount
	 * @param status
	 * @param date_created
	 */
	public Order(User user_id, Double amount, String status) {
		super();
		this.user_id = user_id;
		this.amount = amount;
		Status = status;
		this.date_created = new Date();
	}
	/**
	 * 
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", user_id=" + user_id + ", amount=" + amount + ", Status=" + Status
				+ ", date_created=" + date_created + "]";
	}

	
	
}
