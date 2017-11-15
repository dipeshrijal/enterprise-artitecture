package exercise03_2;

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

import exercise03_2.d.Customer;
import exercise03_2.d.Reservation;

public class MainD {

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

			System.out.println("=======Question D========");

			Customer cust = new Customer("Dipesh Rijal");
			Reservation res1 = new Reservation(parseDate("12/12/2012"));
			Reservation res2 = new Reservation(parseDate("12/11/2011"));

			List<Reservation> rlist = new ArrayList<Reservation>();
			rlist.add(res1);
			rlist.add(res2);

			cust.setReservationList(rlist);

			session.persist(cust);

			List<Customer> clist = new ArrayList<Customer>();

			clist = session.createQuery("from Customer").list();

			for (Customer customer : clist) {
				System.out.println("============================================");
				System.out.println("Customer Name = " + customer.getName());

				for (Reservation reser : cust.getReservationList()) {
					System.out.println("Reservation Date = " + reser.getDate());
				}

				System.out.println("============================================");
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
