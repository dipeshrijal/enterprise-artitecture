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

			System.out.println("=======Question A========");

			Department department = new Department("Compro");

			Employee emp1 = new Employee("Dipesh");
			Employee emp2 = new Employee("Jane");
			Employee emp3 = new Employee("John");

			emp1.setDepartment(department);
			emp2.setDepartment(department);
			emp3.setDepartment(department);

			List<Employee> empList = new ArrayList<Employee>();

			empList.add(emp1);
			empList.add(emp2);
			empList.add(emp3);

			department.setEmployeeList(empList);

			session.persist(department);

			List<Employee> empList1 = (List<Employee>) session.createQuery("from Employee ").list();
			for (Employee emp : empList1) {
				System.out.println("Employee Details : " + emp.getName());
				System.out.println("Employee Department Details: " + emp.getDepartment().getName());
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
