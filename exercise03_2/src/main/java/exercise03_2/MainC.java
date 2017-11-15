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

import exercise03_2.c.Course;
import exercise03_2.c.Student;

public class MainC {

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
