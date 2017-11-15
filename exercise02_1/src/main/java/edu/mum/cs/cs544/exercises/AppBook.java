package edu.mum.cs.cs544.exercises;

import java.text.DateFormat;
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

public class AppBook {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void main(String[] args) throws ParseException {
        // Hibernate placeholders
        Session session = null;
        Transaction tx = null;
        
        //Part - 1
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            
            DateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            Date d1 = df.parse("12-10-2013");
            Date d2 = df.parse("10-10-2008");
            Date d3 = df.parse("12-1-2010");

            // Create new instance of Book and set values in it
            Book book1 = new Book("Intro to Java", "123456","Dipesh Rijal", 33.00, d1);
            Book book2 = new Book("Intro to PHP", "123457","John Doe", 27.00, d2);
            Book book3 = new Book("Intro to Python", "123458","Jane Doe", 52.00, d3);

            session.persist(book1);
            session.persist(book2);
            session.persist(book3);

            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        //Part - 2
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all books
            @SuppressWarnings("unchecked")
			List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", Author= " + book.getAuthor() + ", price= " + book.getPrice());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        //part - 3
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve one book
            Book book1 = (Book)session.get(Book.class, new Integer(2));
            book1.setTitle("Change Title");
            book1.setPrice(42.00);
            
            Book book2 = (Book)session.load(Book.class, new Integer(3));
            session.delete(book2);
            
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        //part - 4
        try {
        	session = sessionFactory.openSession();
            tx = session.beginTransaction();

            // retieve all books
            @SuppressWarnings("unchecked")
            List<Book> bookList = session.createQuery("from Book").list();
            for (Book book : bookList) {
                System.out.println("Title= " + book.getTitle() + ", ISBN= "
                        + book.getISBN() + ", Author= " + book.getAuthor() + ", price= " + book.getPrice());
            }
            tx.commit();

        } catch (HibernateException e) {
            if (tx != null) {
                System.err.println("Rolling back: " + e.getMessage());
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        

        // Close the SessionFactory (not mandatory)
        sessionFactory.close();
        System.exit(0);
    }
}
