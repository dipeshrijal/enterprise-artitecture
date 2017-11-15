package cs544.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue
	private long coursenumber;
	private String name;
	private String grade;

	public Course(String name, String grade) {
		this.name = name;
		this.grade = grade;
	}

	public Course() {
	}

	public long getCoursenumber() {
		return coursenumber;
	}

	public void setCoursenumber(long coursenumber) {
		this.coursenumber = coursenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
