package com.springcore.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.springcore.entity.Order;
import com.springcore.entity.OrderedProducts;

public class OrderedProductDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	public List <OrderedProducts> getOrderedProduct(Order order_id){
		List<OrderedProducts> ans= new ArrayList<>();
		OrderedProducts op= new OrderedProducts();
		op.setOrder_id(order_id);
		try {
			
			ans=hibernateTemplate.findByExample(op);
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("some error has occured");
			return null;
		}
		
		return ans;
	}
	
}
