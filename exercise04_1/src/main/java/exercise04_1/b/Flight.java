package exercise04_1.b;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "flight")
public class Flight {
	@Id
	@GeneratedValue
	private int id;
	private String flightumber;
	@Column(name = "from_destination")
	private String from;
	@Column(name = "to_destination")
	private String to;
	@Temporal(TemporalType.DATE)
	private Date date;

	public Flight() {
	}

	public Flight(String flightumber, String from, String to, Date date) {
		this.flightumber = flightumber;
		this.from = from;
		this.to = to;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightumber() {
		return flightumber;
	}

	public void setFlightumber(String flightumber) {
		this.flightumber = flightumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
