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

import exercise03_2.a.Department;
import exercise03_2.a.Employee;
import exercise03_2.b.Book;
import exercise03_2.b.Publisher;
import exercise03_2.c.Course;
import exercise03_2.c.Student;
import exercise03_2.d.Customer;
import exercise03_2.d.Reservation;
import exercise03_2.f.Office;

public class Main {

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

			// Department department = new Department("Compro");
			//
			// Employee emp1 = new Employee("Dipesh");
			// Employee emp2 = new Employee("Jane");
			// Employee emp3 = new Employee("John");
			//
			// emp1.setDepartment(department);
			// emp2.setDepartment(department);
			// emp3.setDepartment(department);
			//
			// List<Employee> empList = new ArrayList<Employee>();
			//
			// empList.add(emp1);
			// empList.add(emp2);
			// empList.add(emp3);
			//
			// department.setEmployeeList(empList);
			//
			// session.persist(department);
			//
			// List<Employee> empList1 = (List<Employee>) session.createQuery("from Employee
			// ").list();
			// for (Employee emp : empList1) {
			// System.out.println("Employee Details : " + emp.getName());
			// System.out.println("Employee Department Details: " +
			// emp.getDepartment().getName());
			// System.out.println("============================================");
			// }

			System.out.println("=======Question B========");

			Book book = new Book("123546", "EA", "Dipesh");
			Publisher publisher = new Publisher("Ekata");
			book.setPublisher(publisher);

			session.persist(book);

			List<Book> bookList = session.createQuery("from Book").list();
			for (Book bk : bookList) {
				System.out.println("title = " + bk.getTitle() + ", isbn= " + bk.getIsbn() + ", publisher= "
						+ bk.getPublisher().getName());
			}

			System.out.println("=======Question C========");
			Student student1 = new Student("Dipesh", "Rijal");
			Student student2 = new Student("Jane", "Doe");
			Course course1 = new Course(123, "EA");
			Course course2 = new Course(133, "MWA");
			Course course3 = new Course(222, "WAA");

			student1.getCourseList().add(course1);
			student1.getCourseList().add(course2);

			student2.getCourseList().add(course3);
			student2.getCourseList().add(course1);

			session.persist(student1);
			session.persist(student2);

			List<Student> studentList = new ArrayList<Student>();
			studentList = session.createQuery("from Student").list();

			for (Student student : studentList) {
				System.out.println("============================================");
				System.out.println("Student Name = " + student.getFirstname() + " " + student.getLastname());

				for (Course course : student.getCourseList()) {
					System.out.println("Course name = " + course.getName());
				}
				System.out.println("============================================");
			}

			System.out.println("=======Question D========");

			// Customer cust = new Customer("Dipesh Rijal");
			// Reservation res1 = new Reservation(parseDate("12/12/2012"));
			// Reservation res2 = new Reservation(parseDate("12/11/2011"));
			//
			// List<Reservation> rlist = new ArrayList<Reservation>();
			// rlist.add(res1);
			// rlist.add(res2);
			//
			// cust.setReservationList(rlist);
			//
			// session.persist(cust);
			//
			// List<Customer> clist = new ArrayList<Customer>();
			//
			// clist = session.createQuery("from Customer").list();
			//
			// for (Customer customer : clist) {
			// System.out.println("============================================");
			// System.out.println("Customer Name = " + customer.getName());
			//
			// for (Reservation reser : cust.getReservationList()) {
			// System.out.println("Reservation Date = " + reser.getDate());
			// }
			//
			// System.out.println("============================================");
			// }

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

			session.persist(reserve1);
			session.persist(reserve2);
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
