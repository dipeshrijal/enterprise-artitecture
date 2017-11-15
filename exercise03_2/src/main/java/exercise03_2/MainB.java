package exercise03_2;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import exercise03_2.b.Book;
import exercise03_2.b.Publisher;

public class MainB {

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

			System.out.println("=======Question B========");

			Book book = new Book("123546", "EA", "Dipesh");
			Book book2 = new Book("1233346", "WAA", "John");
			Publisher publisher = new Publisher("Ekata");
			
			book.setPublisher(publisher);
			book2.setPublisher(publisher);

			session.persist(book);
			session.persist(book2);

			List<Book> bookList = session.createQuery("from Book").list();
			for (Book bk : bookList) {
				System.out.println("title = " + bk.getTitle() + ", isbn= " + bk.getIsbn() + ", publisher= "
						+ bk.getPublisher().getName());
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
