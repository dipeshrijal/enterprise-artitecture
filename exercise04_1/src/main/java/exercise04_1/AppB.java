package exercise04_1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import exercise04_1.b.Flight;
import exercise04_1.b.Passenger;

public class AppB {

	public static final SessionFactory sessionFactory;
	public static final ServiceRegistry serviceRegistry;

	static {
		Configuration configuration = new Configuration();
		configuration.configure();

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Passenger passenger1 = new Passenger("Dipesh");
			
			Flight flight1 = new Flight("ACZ101", "Cedar Rapids", "Austin", parseDate("12/12/2012"));
			Flight flight2 = new Flight("ACZ102", "Austin", "California", parseDate("12/12/2013"));
			
			passenger1.addFlight(flight1);
			passenger1.addFlight(flight2);
			
			session.persist(passenger1);

			

			@SuppressWarnings("unchecked")
			List<Passenger> passengerList = session.createQuery("from Passenger").list();

			for (Passenger passenger : passengerList) {
				System.out.println(passenger.getName());
				System.out.println("================================================");
				for (Flight flight : passenger.getFlightList()) {
					System.out.println(flight.getFlightumber());
					System.out.println(flight.getFrom());
					System.out.println(flight.getDate());
					System.out.println("================================================");
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
