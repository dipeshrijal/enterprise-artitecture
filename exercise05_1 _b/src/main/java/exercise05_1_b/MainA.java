package exercise05_1_b;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainA {

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

	static {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Customer customer = new Customer("Dipesh", "Rijal");

			Order order1 = new Order(parseDate("12/12/2012"));
			Order order2 = new Order(parseDate("12/10/2012"));

			order1.setCustomer(customer);
			order2.setCustomer(customer);

			List<Order> orderList = new ArrayList<Order>();
			orderList.add(order1);
			orderList.add(order2);

			customer.setOrderList(orderList);

			OrderLine orderLine1 = new OrderLine(5);
			OrderLine orderLine2 = new OrderLine(10);
			OrderLine orderLine3 = new OrderLine(15);
			OrderLine orderLine4 = new OrderLine(20);

			List<OrderLine> orderLineList1 = new ArrayList<OrderLine>();
			orderLineList1.add(orderLine1);
			orderLineList1.add(orderLine2);

			order1.setOrderLineList(orderLineList1);

			List<OrderLine> orderLineList2 = new ArrayList<OrderLine>();
			orderLineList2.add(orderLine3);
			orderLineList2.add(orderLine4);

			order2.setOrderLineList(orderLineList2);

			Product product1 = new CD("Dipesh", "Mac Book", "This is mac book");
			Product product2 = new DVD("POP", "iPhone", "This is iPhone");
			Product product3 = new Book("PHP", "Dell", "This is iPhone");
			Product product4 = new CD("Rijal", "Acer", "This is iPhone");

			orderLine1.setProduct(product1);
			orderLine2.setProduct(product2);
			orderLine3.setProduct(product3);
			orderLine4.setProduct(product4);

			session.persist(customer);

			List<Customer> custList = (List<Customer>) session.createQuery("from Customer").list();
			for (Customer cust : custList) {
				System.out.println("Customer Name : " + cust.getFirstname());

				for (Order order : cust.getOrderList()) {
					System.out.println("Order Date = " + order.getDate());
					System.out.println("============================================");
					for (OrderLine orderline : order.getOrderLineList()) {
						System.out.println("OrderLine Quantity =  " + orderline.getQuantity());
						System.out.println("Product Name = " + orderline.getProduct().getName());
						System.out.println("Product Name = " + orderline.getProduct().getClass().getSimpleName());
						System.out.println("============================================");
					}
				}
			}

			tx.commit();

		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	private static Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return d;
	}

}
