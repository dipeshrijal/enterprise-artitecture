package cs544.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cs544.webapp.dao.StudentDAO;
import cs544.webapp.domain.Student;

@Service
public class StudentService {
	
	@Autowired
	private StudentDAO studentDAO;

	public StudentService() {
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Student getStudent(long studentid) {
		this.studentDAO.createStudents();
		return studentDAO.load(studentid);
	}
	
	public List<Student> getAllStudents() {
		return studentDAO.getAllStudents();
	}
}
