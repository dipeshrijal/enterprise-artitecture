package cs544.exercise16_2;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentService {
	private StudentDAO studentDAO;

	public StudentService() {
	}

	public void setStudentDAO(StudentDAO studentDAO) {
		this.studentDAO = studentDAO;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Student getStudent(long studentid) {
		this.studentDAO.createStudents();
		return studentDAO.load(studentid);
	}
}
