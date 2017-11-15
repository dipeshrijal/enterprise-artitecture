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

import exercise03_2.b.Book;
import exercise03_2.d.Customer;
import exercise03_2.d.Reservation;

public class MainE {

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

			System.out.println("=======Question E========");

			Customer cust1 = new Customer("Second Customer");
			Reservation reserve1 = new Reservation(parseDate("01/01/2017"));
			Reservation reserve2 = new Reservation(parseDate("12/11/2011"));

			Book book2 = new Book("987667", "PHP", "Jane");

			reserve1.setBook(book2);
			reserve2.setBook(book2);

			List<Reservation> reservelist = new ArrayList<Reservation>();
			reservelist.add(reserve1);
			reservelist.add(reserve2);

			cust1.setReservationList(reservelist);
			
			session.persist(cust1);

			List<Customer> culist = new ArrayList<Customer>();

			culist = session.createQuery("from Customer").list();

			for (Customer cu : culist) {
				System.out.println("============================================");
				System.out.println("Customer Name = " + cu.getName());

				for (Reservation reser : cu.getReservationList()) {
					System.out.println("Reservation Date = " + reser.getDate());

					System.out.println("Book Name = " + reser.getBook().getTitle());
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
