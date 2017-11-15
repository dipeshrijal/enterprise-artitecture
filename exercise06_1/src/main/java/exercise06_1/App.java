package exercise06_1;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class App {

	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;

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

			Patient p1 = new Patient("Dipesh", "1000 N", "52557", "Fairfield");
			Doctor d1 = new Doctor("ENT", "John", "Doe");
			Payment pa1 = new Payment("12/12/2012", 300.0);

			Appointment ap1 = new Appointment("01/01/2013");

			ap1.setPatient(p1);
			ap1.setPayment(pa1);
			ap1.setDoctor(d1);

			session.persist(ap1);
			
			@SuppressWarnings("unchecked")
			List<Appointment> appointmentList = session.createQuery("from Appointment").list();

			for (Appointment appointment : appointmentList) {
				System.out.println("Appointment Date = " + appointment.getAppdate());
				System.out.println("Doctor Name = " + appointment.getDoctor().getFirstname() + " "
						+ appointment.getDoctor().getLastname());
				System.out.println("Patient Name = " + appointment.getPatient().getName());
				System.out.println("Payment Date = " + appointment.getPayment().getPaydate());
				System.out.println("Payment Amount = " + appointment.getPayment().getAmount());

				System.out.println("================================================");
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

}
