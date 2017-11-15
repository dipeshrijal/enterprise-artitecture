package cs544.webapp.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.webapp.domain.Course;
import cs544.webapp.domain.Student;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class StudentDAO {

	@Autowired
	private SessionFactory sf;

	public StudentDAO() {
	}

	public void createStudents() {
		Student student = new Student("Frank", "Brown");
		Course course1 = new Course("Java", "A");
		Course course2 = new Course("Math", "B-");
		student.addCourse(course1);
		student.addCourse(course2);

		sf.getCurrentSession().persist(student);
	}
	
	public List<Student> getAllStudents() {
		@SuppressWarnings("unchecked")
		List<Student> students = sf.getCurrentSession().createQuery("from Student").list();
		
		return students;
	}

	public Student load(long studentid) {
		Student student = (Student) sf.getCurrentSession().get(Student.class, studentid);

		return student;
	}
}
