package com.springcore.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.springcore.entity.Order;
import com.springcore.entity.User;

public class OrderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	
	public Order getOrder(int id) {
		Order order=null;
		try {
			order=hibernateTemplate.get( Order.class, id);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error:" +e.getLocalizedMessage());
			return null;
		}
		return order;
	}

	public Set<Order> getAllOrders(String search) {
		Set<Order> result = new HashSet<>();
		List<Order> list = new ArrayList<>();
		List<Integer> ids = new ArrayList<>();
		Order order = new Order();
		try {

			if (Character.isDigit(search.trim().charAt(0))) {
				// search by id
				try {
					list.add(hibernateTemplate.get(Order.class, Integer.parseInt(search)));
					ids.add(list.get(0).getId());
					result.add(list.get(0));
				} catch (Exception e) {
					// TODO: handle exception
				}
				list.clear();
				// search by user id
				try {
					User user = new User();
					user.setId(Integer.parseInt(search));
					order.setUser_id(user);
					list = hibernateTemplate.findByExample(null, order);
					order.setUser_id(null);

					for (int i = 0; i < list.size(); i++) {
						if (!ids.contains(list.get(i).getId())) {
							ids.add(list.get(i).getId());
							result.add(list.get(i));
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				list.clear();
				// search by date created
				try {
					order.setDate_created(new Date(search));
					list=hibernateTemplate.findByExample(null, order);
					for (int i = 0; i < list.size(); i++) {
						if (!ids.contains(list.get(i).getId())) {
							ids.add(list.get(i).getId());
							result.add(list.get(i));
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			list.clear();

			try {
				// search by status
				order.setStatus(search);
				list=hibernateTemplate.findByExample(null, order);
				order.setStatus(null);
				for (int i = 0; i < list.size(); i++) {
					if (!ids.contains(list.get(i).getId())) {
						ids.add(list.get(i).getId());
						result.add(list.get(i));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("some error has occured: " + e.getLocalizedMessage());
			return null;
		}
		return result;
	}
	
	
	
	@Transactional
	public boolean updatestatus(Order o) {
		try {
			hibernateTemplate.update(o);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("error occured: "+e.getLocalizedMessage());
			return false;
		}
		return true;
	}

}
