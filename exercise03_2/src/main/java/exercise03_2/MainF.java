package exercise03_2;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import exercise03_2.a.Department;
import exercise03_2.a.Employee;
import exercise03_2.f.Office;

public class MainF {

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

			System.out.println("=======Question F========");

			Department department1 = new Department("MBA");

			Employee emp4 = new Employee("ABC");
			Employee emp5 = new Employee("CDE");
			Employee emp6 = new Employee("EFG");

			Office office = new Office(101, "Verill Hall");

			emp4.setDepartment(department1);
			emp5.setDepartment(department1);
			emp6.setDepartment(department1);

			emp4.setOffice(office);
			emp5.setOffice(office);
			emp6.setOffice(office);

			List<Employee> eList = new ArrayList<Employee>();

			eList.add(emp4);
			eList.add(emp5);
			eList.add(emp6);

			department1.setEmployeeList(eList);

			session.persist(department1);

			List<Employee> el = (List<Employee>) session.createQuery("from Employee ").list();
			for (Employee em : el) {
				System.out.println("Employee Details : " + em.getName());
				System.out.println("Employee Department Details: " + em.getDepartment().getName());
				System.out.println("Employee Office Details: " + em.getOffice().getBuilding());
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

}
