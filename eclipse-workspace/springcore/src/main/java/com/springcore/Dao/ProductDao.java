package com.springcore.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.springcore.entity.Product;

public class ProductDao {

	private HibernateTemplate hibernateTemplate;

//	@Autowired
//	private ProductRepositoryInmp prepo;
//	
//	
//	public ProductRepositoryInmp getPrepo() {
//		return prepo;
//	}
//
//	public void setPrepo(ProductRepositoryInmp prepo) {
//		this.prepo = prepo;
//	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	/**
	 * 
	 */
	public ProductDao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Transactional
	public boolean addProduct(Product product) {
		if (hibernateTemplate == null) {
			System.out.println("----ERROR in connection----");
			return false;
		}
		try {
			hibernateTemplate.save(product);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public List<Product> getAllProduct() {
		List<Product> allProduct = null;
		try {
			allProduct = hibernateTemplate.loadAll(Product.class);
		} catch (Exception e) {
			return null;
		}

		return allProduct;
	}

	public List<Product> getProductDetails(Product product, int fetchOperation) {
		List<Product> products = new ArrayList<>();
		for (Product p : products)
			p = new Product();

		if (fetchOperation == 1) {
			try {
				products.add(hibernateTemplate.get(Product.class, product.getId()));
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		else {
			try {

				products = hibernateTemplate.findByExample(null,product);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getLocalizedMessage());
			}
		}
		
		System.out.println(products.size());
		return products;

	}

	// get single product

	public Product getProduct(int id) {
		Product ans = null;
		try {
			ans = hibernateTemplate.get(Product.class, id);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return ans;
	}

	// update product

	@Transactional
	public boolean updateProduct(Product p) {
		try {
			hibernateTemplate.update(p);
			
		} catch (Exception e) {
			System.out.println("some error has occure : " + e.getLocalizedMessage());
			return false;
		}
		return true;

	}

	// deleting product

	@Transactional
	public boolean deleteProduct(Product p) {

		try {

			hibernateTemplate.delete(p);
			System.out.println("processing...");
		} catch (Exception e) {
			System.out.println("some error occure");
			System.out.println(e.getLocalizedMessage());
			return false;
		}

		return true;
	}

}
