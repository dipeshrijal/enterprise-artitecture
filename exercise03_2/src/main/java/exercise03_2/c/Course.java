package exercise03_2.c;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue
	private int id;
	private int coursenumber;
	private String name;
	@ManyToMany(mappedBy = "courseList")
	private List<Student> studentList = new ArrayList<Student>();

	public Course() {
	}

	public Course(int coursenumber, String name) {
		this.coursenumber = coursenumber;
		this.name = name;
	}

	public int getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(int coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

}
