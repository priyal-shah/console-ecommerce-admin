package com.springcore;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.springcore.Dao.CommentDao;
import com.springcore.Dao.OrderDao;
import com.springcore.Dao.OrderedProductDao;
import com.springcore.Dao.ProductDao;
import com.springcore.entity.Comment;
import com.springcore.entity.Order;
import com.springcore.entity.OrderedProducts;
import com.springcore.entity.Product;
import com.springcore.entity.User;


public class AdminPage {
	static Scanner sc = new Scanner(System.in);
	static ApplicationContext cn = new ClassPathXmlApplicationContext("config.xml");
	static ProductDao productDao = (ProductDao) cn.getBean("productDao");
	static OrderDao orderDao = (OrderDao) cn.getBean("orderDao");
	static OrderedProductDao orderedProductDao = (OrderedProductDao) cn.getBean("orderedProductDao");
	static CommentDao commentDao=(CommentDao)cn.getBean("commentDao");
	
	static public void homeForAdmin(User user) {

		System.out.println("\n\n\n\t\t<======hello " + user.getName() + "=========>\n\n\n");

		boolean logout = false;
		while (!logout) {
			System.out.println("for operation related to product enter :1\n"
					+ "for operation related to order enter   :2\n" + "for operations related to comments enter:3\n"
					+ "to logout enter                        :0");
			try {
				int operation = sc.nextInt();

				switch (operation) {
				case 1: {

					System.out.println("\t\t=========PRODUCT OPERATION=========\n\n");
					boolean goTohomePage = false;
					while (!goTohomePage) {
						System.out.println("\n\nfor adding a new product enter    :1\n"
								+ "for checking stock enter          :2\n" + "for checking product details enter:3\n"
								+ "for updating product details enter:4\n" + "for deleting product enter        :5\n"
								+ "go to home page enter             :6");
						int productOperation = sc.nextInt();

						goTohomePage = productFunction(productOperation);
					}

					break;
				}
				case 2: {
					// operation related to orders

					System.out.println("\t\t=========ORDERS OPERATION=========\n\n");
					boolean goTohomePage = false;
					while (!goTohomePage) {
						System.out.println("for checking orders enter   :1\n" + "updating order status enter :2\n"
								+ "to go to home page enter    :0");
						int OrderOperation = sc.nextInt();

						goTohomePage = orderFunction(OrderOperation);
					}

					break;
				}
				case 3: {
					// operations related to comments

					System.out.println("\t\t=========COMMENTS OPERATION=========\n\n");
					boolean goTohomePage = false;
					while (!goTohomePage) {
						System.out.println("for checking comments enter   :1\n" + "for deleting comment enter :2\n"
								+ "to go to home page enter    :0");
						int CommentOperation = sc.nextInt();

						goTohomePage = commentFunction(CommentOperation);
					}

					break;
				}

				case 0: {
					logout = true;
					break;
				}
				default:
					System.err.println("please enter a valid operation");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

	}

	private static boolean productFunction(int productOperation) {
		switch (productOperation) {
		case 1: {
			// add new product
			System.out.println("\t----Add new product----\n\n");

			System.out.println("enter name of product");
			String product_name_dummy = sc.nextLine();
			String product_name = sc.nextLine();

			System.out.println("enter price of product");
			int product_price = sc.nextInt();

			System.out.println("enter description of product");
			String product_desc_dummy = sc.nextLine();
			String product_desc = sc.nextLine();

			System.out.println("enter category of product");
			String product_category = sc.nextLine();

			String tags = "";
			String tag = "";
			System.out.println("enter tags (enter \'#\' if completed with tags )");
			while (true) {
				tag = "";
				tag = sc.nextLine();
				tags += tag + ",";
				if (tag.trim().length() > 0 && tag.trim().charAt(0) == '#')
					break;
			}

			System.out.println("enter quantity");
			int quantity = sc.nextInt();

			System.out.println("processing your request");
			Product product = new Product(product_name, product_price, product_desc, tags, product_category, quantity);
			boolean result = productDao.addProduct(product);
			if (result)
				System.out.println("\n\t\t======Product added successfully=====\n");

			return false;
		}
		case 2: {
			// for checking stock
			System.err.println("\n\n\n");
			List<Product> allProducts = productDao.getAllProduct();
			if (allProducts == null) {
				System.out.println("Somethig went wrong please try again");
			} else {
				System.out
						.println("\nProduct id\t\t\t " + "Product name\t\t\t\t " + "Product Price\t\t\t " + "Quantity");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------\n");
				for (int i = 0; i < allProducts.size(); i++) {
					System.out
							.println(allProducts.get(i).getId() + "\t\t\t\t" + allProducts.get(i).getName() + "\t\t\t\t"
									+ allProducts.get(i).getPrice() + "\t\t\t\t" + allProducts.get(i).getQuantity());
				}
			}
			return false;
		}
		case 3: {
			// for checking product details
			List<Product> ProductList = null;
			System.out.println("to fetch product by id enter          :1\n"
					+ "to fetch product by name enter        :2\n" + "to fetch product by category enter    :3\n"
					+ "to fetch product by quantity enter    :4\n" + "to fetch product by date created enter:5");
			int fetchOperation = sc.nextInt();
			switch (fetchOperation) {
			case 1: {
				// by id
				int id = sc.nextInt();
				Product product = new Product();
				product.setId(id);
				ProductList = productDao.getProductDetails(product, fetchOperation);
				break;
			}
			case 2: {
				// by name
				String dummyname = sc.nextLine();
				String name = sc.nextLine();
				Product product = new Product();
				product.setName(name);
				ProductList = productDao.getProductDetails(product, fetchOperation);
				break;
			}
			case 3: {
				// by category
				String category = sc.nextLine();
				Product product = new Product();
				product.setCategory(category);
				ProductList = productDao.getProductDetails(product, fetchOperation);
				break;
			}
			case 4: {
				// by quantity
				int quantity = sc.nextInt();
				Product product = new Product();
				product.setQuantity(quantity);
				ProductList = productDao.getProductDetails(product, fetchOperation);
				break;
			}
			case 5: {
				// by date
				String dummydate = sc.next();
				String datestring = sc.next();
				Date date = new Date(datestring);
				Product product = new Product();
				product.setDate_created(date);
				ProductList = productDao.getProductDetails(product, fetchOperation);
				break;
			}
			default:
				System.out.println("enter a valid operation");
			}

			System.out.println("id\t\t" + "name\t\t\t" + "price\t\t" + "category\t\t\t" + "date craeted\t\t"
					+ "quantity\t\ttags\t\t" + "description");

			for (int i = 0; i < ProductList.size(); i++) {
				Product p = ProductList.get(i);
				System.out.println(
						p.getId() + "\t\t" + p.getName() + "\t" + "\t" + p.getPrice() + "\t\t" + p.getCategory() + "\t"
								+ "\t\t" + p.getDate_created() + "\t\t" + p.getTag() + "\t\t" + p.getDesc());

			}

			return false;
		}
		case 4: {
			// update product

			System.out.print("enter the id of the product you want to update: ");
			int id = sc.nextInt();
			Product p = productDao.getProduct(id);

			System.out.println("\n enter \'y\' for yes and \'n\' for No\n");

			// name
			System.out.print("Name: " + p.getName() + "\n Do you want to update name: ");
			char ans = sc.next().trim().toLowerCase().charAt(0);
			sc.nextLine();
			if (ans == 'y') {
				System.out.println("enter new name");
				p.setName(sc.nextLine());
			}

			// price
			System.out.print("\n Price :" + p.getPrice() + "\n Do you want to update the price: ");

			ans = sc.next().trim().toLowerCase().charAt(0);

			if (ans == 'y') {
				System.out.println("enter new Price");
				p.setPrice(sc.nextDouble());
				sc.nextLine();
			}

			// category
			System.out.print("\n Category :" + p.getCategory() + "\n Do you want to update the Category: ");

			ans = sc.next().trim().toLowerCase().charAt(0);

			if (ans == 'y') {

				System.out.println("enter new Category");
				p.setCategory(sc.nextLine());
			}

			// quantity
			System.out.print("\n Quantity :" + p.getQuantity() + "\n Do you want to update the Quantity: ");

			ans = sc.next().trim().toLowerCase().charAt(0);

			if (ans == 'y') {
				System.out.println("enter new Quantity");
				p.setQuantity(sc.nextInt());
				sc.nextLine();
			}

			// tags
			System.out.print("\n tags :");
			for (String s : p.getTag().split(","))
				System.out.print(s + " ,");

			System.out.println("\n Do you want to update the Tags: ");

			ans = sc.next().trim().toLowerCase().charAt(0);

			if (ans == 'y') {
				System.out.println("enter new Tags");
				String tags = "";
				String tag = "";
				while (tag != "#") {
					tag = sc.nextLine();
					if (tag != "#")
						tags += tag + ",";
				}

				p.setTag(tags);
			}

			// desc
			System.out.print("\n Description :" + p.getDesc() + "\n Do you want to update the Description: ");

			ans = sc.next().trim().toLowerCase().charAt(0);

			if (ans == 'y') {
				System.out.println("enter new Description");
				p.setDesc(sc.nextLine());
			}

			p.setDate_created(new Date());

			if (productDao.updateProduct(p))
				System.out.println("Product updated successfully");

			return false;
		}
		case 5: {
			// delete product

			System.out.println("enter the Id of the product you want to delete");
			int id = sc.nextInt();
			Product productToBeDeleted = productDao.getProduct(id);
			System.out.println("PRODUCT TO BE DELETED: " + productToBeDeleted);
			System.out.println("enter y to continue else enter n");
			char yes = sc.next().trim().toLowerCase().charAt(0);
			if (yes == 'y' && productDao.deleteProduct(productToBeDeleted))
				System.out.println("Deleted sucessfully");

			return false;
		}
		case 6: {
			// return to home page
			return true;
		}
		default:
			System.out.println("enter a valid operation please");
			return false;
		}

	}

	public static boolean orderFunction(int orderOperation) {
		switch (orderOperation) {
		case 1: {
			// checking orders
			List<Order> resultOrders = new ArrayList<>();
			System.out.print("enter search : ");
			sc.nextLine();
			String search = sc.nextLine();
			System.out.println("\n");

			resultOrders.addAll(orderDao.getAllOrders(search));

			if (resultOrders != null) {
				long len = resultOrders.size();
				for (int i = 0; i < len; i++) {
					Order o = resultOrders.get(i);

					// fetching orderd products
					List<OrderedProducts> list = orderedProductDao.getOrderedProduct(o);
					System.out.println(o);
					System.out.println("product details...");
					for (int j = 0; j < list.size(); j++) {
						OrderedProducts op = list.get(j);
						System.out.println(op.getProduct_id().getId() + " " + op.getProduct_id().getName() + " "
								+ op.getQuantity() + " " + op.getPrice());
					}
					System.out.println(
							"------------------------------------------------------------------------------------------------------");
				}
			}

			System.out.println("\n");
			return false;
		}
		case 2: {
			// updating status
			System.out.println("Enter order id ");
			int orderId = sc.nextInt();
			Order order = orderDao.getOrder(orderId);
			System.out.println("order details: " + order.getId() + " -> " + order.getStatus());

			System.out.println("instructions...\n " + "order placed     : 1\n " + "order packed     :2 \n "
					+ "order dispacthed :3 \n " + "delivered        : 4\n " + "fail to deliver  :5 \n "
					+ "order return     :6");

			int orderStausOperation = sc.nextInt();
			switch (orderStausOperation) {
			case 1: {
				order.setStatus("order placed");
				break;
			}
			case 2: {
				order.setStatus("order packed");
				break;
			}
			case 3: {
				order.setStatus("order dispacthed");
				break;
			}
			case 4: {
				order.setStatus("delivered");
				break;
			}
			case 5: {
				order.setStatus("fail to deliver");
				break;
			}
			case 6: {
				order.setStatus("order return");
				break;
			}
			default:
				System.out.println("please enter a valid operation");
			}

			if (orderDao.updatestatus(order))
				System.out.println("status updated sucessfully");

			return false;
		}
		case 0: {
			// go to home page
			return true;
		}
		default:
			System.out.println("please enter a valid operation");
			return false;
		}

	}

	public static boolean commentFunction(int CommentOperation) {
		switch (CommentOperation) {
		case 1: {

			// check comments
			Comment c = new Comment();
			System.out.println("do you want to enter product_id");
			String yes=sc.next();
			if (yes.trim().charAt(0)=='y') {
				System.out.println("enter product id");
				c.setProduct_id(sc.nextInt());
			}
			yes="";
			System.out.println("do you want to enter user_id");
			 yes=sc.next();
			if (yes.trim().charAt(0)=='y'){
				System.out.println("enter user id");
				c.setUser_id(sc.nextInt());
			}
			yes="";
			System.out.println("do you want to enter date");
			 yes=sc.next();
			if (yes.trim().charAt(0)=='y') {
				System.out.println("enter year");
				int year=sc.nextInt();
				System.out.println("enter month");
				int month=sc.nextInt();
				System.out.println("enter date");
				int day=sc.nextInt();
				Date date= new Date();
				date.setYear(year);
				date.setMonth(month);
				date.setDate(day);
				c.setDate_posted(date);
				 
			}
			List<Comment> ans= new ArrayList<>();
			ans=commentDao.getComment(c);
			if(ans!=null) {
				for(int i=0;i<ans.size();i++) {
					System.out.println(ans.get(i));
				}
			}

			return false;
		}
		case 2: {
			// delete comment
			System.out.println("enter comment id");
			int comment_id=sc.nextInt();
			Comment result=commentDao.getCommentById(comment_id);
			System.out.println("comment to be deleted ->"+result);
			System.out.println("enter y to continue");
			sc.nextLine();
			String yes=sc.nextLine();
			if(yes.trim().charAt(0)=='y' && commentDao.deleteComment(result)) System.out.println("comment successfully deleted");
			return false;
		}
		case 0: {
			// go to home page
			return true;
		}
		default:
			System.out.println("please enter a valid operation");
			return false;
		}
	}

}
