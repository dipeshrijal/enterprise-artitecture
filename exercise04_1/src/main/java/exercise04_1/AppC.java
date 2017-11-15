package exercise04_1;

import java.util.List;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import exercise04_1.c.School;
import exercise04_1.c.Student;

public class AppC {

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

			School school = new School("Maharishi");

			Student student1 = new Student("Dipesh", "Rijal");
			Student student2 = new Student("Amina", "Rai");

			Integer stu1 = (Integer) session.save(student1);
			Integer stu2 = (Integer) session.save(student2);

			school.addStudent(stu1, student1);
			school.addStudent(stu2, student2);

			session.persist(school);

			@SuppressWarnings("unchecked")
			List<School> schoolList = session.createQuery("from School").list();

			for (School sc : schoolList) {
				System.out.println(sc.getName());
				for (Entry<Integer, Student> student : school.getStudentList().entrySet()) {
					Integer key = student.getKey();
					String name = student.getValue().getFirstname();
					System.out.println(key + " " + name);
				}
//				for (Integer name : school.getStudentList().keySet()) {
//					String key = name.toString();
//					String value = school.getStudentList().get(name).getFirstname();
//					System.out.println(key + " " + value);
//				}
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
