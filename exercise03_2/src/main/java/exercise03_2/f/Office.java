package exercise03_2.f;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import exercise03_2.a.Employee;

@Entity
@Table(name = "office")
public class Office {
	@Id
	private int roomnumber;
	private String building;

	@OneToMany(mappedBy = "office", cascade = CascadeType.ALL)
	private List<Employee> employeeList = new ArrayList<Employee>();

	public Office() {
	}

	public Office(int roomnumber, String building) {
		this.roomnumber = roomnumber;
		this.building = building;
	}

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

}
