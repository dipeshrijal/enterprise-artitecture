package exercise03_2.a;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import exercise03_2.f.Office;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private int employeenumber;
	private String name;
	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "office_id")
	private Office office;

	public Employee() {
	}

	public Employee(String name) {
		this.name = name;
	}

	public int getEmployeenumber() {
		return employeenumber;
	}

	public void setEmployeenumber(int employeenumber) {
		this.employeenumber = employeenumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

}
