package com.springcore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordered_products")
public class OrderedProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName = "id")
	private Order order_id;
	@ManyToOne
	@JoinColumn(name="product_id" , referencedColumnName = "id")
	private Product product_id;
	private Integer quantity;
	private Double price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Order order_id) {
		this.order_id = order_id;
	}
	public Product getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @param order_id
	 * @param product_id
	 * @param price
	 */
	public OrderedProducts(Order order_id, Product product_id, Double price, Integer quantity) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.price = price;
		this.quantity=quantity;
	}
	/**
	 * 
	 */
	public OrderedProducts() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderedProducts [id=" + id + ", order_id=" + order_id + ", product_id=" + product_id + ", price="
				+ price + " quantity="+quantity+"]";
	}

	
	
}
