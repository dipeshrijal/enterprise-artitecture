package cs544.webapp.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue
	private long studentid;
	private String firstname;
	private String lastname;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Collection<Course> courselist = new ArrayList<Course>();

	public Student() {
	}

	public Student(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Collection<Course> getCourselist() {
		return courselist;
	}

	public void addCourse(Course course) {
		this.courselist.add(course);
	}

}
