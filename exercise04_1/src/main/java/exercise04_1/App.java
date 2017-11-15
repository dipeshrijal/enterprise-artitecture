package exercise04_1;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import exercise04_1.a.Employee;
import exercise04_1.a.Laptop;

public class App {

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

			Employee emp1 = new Employee("Jane", "Doe");
			Employee emp2 = new Employee("John", "Doe");

			Laptop laptop1 = new Laptop("Apple", "Mac");
			Laptop laptop2 = new Laptop("Dell", "ALienware");
			Laptop laptop3 = new Laptop("Acer", "M5");

			emp1.addLaptop(laptop1);
			emp2.addLaptop(laptop2);
			emp1.addLaptop(laptop3);

			laptop1.setEmployee(emp1);
			laptop2.setEmployee(emp2);
			laptop3.setEmployee(emp1);

			session.persist(emp1);
			session.persist(emp2);

			@SuppressWarnings("unchecked")
			List<Employee> empList = session.createQuery("from Employee").list();

			for (Employee employee : empList) {
				System.out.println(employee.getFirstname());
				System.out.println("================================================");
				for (Laptop laptop : employee.getLaptopList()) {
					System.out.println(laptop.getBrand());
					System.out.println(laptop.getType());
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

}
