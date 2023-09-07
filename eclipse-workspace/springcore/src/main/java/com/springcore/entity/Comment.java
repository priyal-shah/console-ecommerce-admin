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
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer product_id;
	
	private Integer user_id;
	private Date date_posted;
	private String content;
	private Integer rating;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getDate_posted() {
		return date_posted;
	}
	public void setDate_posted(Date date_posted) {
		this.date_posted = date_posted;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	/**
	 * @param product_id
	 * @param user_id
	 * @param content
	 * @param rating
	 */
	public Comment(Integer product_id, Integer user_id, String content, int rating) {
		super();
		this.product_id = product_id;
		this.user_id = user_id;
		this.content = content;
		this.rating = rating;
		this.date_posted=new Date();
	}
	/**
	 * 
	 */
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", product_id=" + product_id + ", user_id=" + user_id + ", date_posted="
				+ date_posted + ", content=" + content + ", rating=" + rating + "]";
	}
	
	
	

}
