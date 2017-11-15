package exercise05_1_b;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderline")
public class OrderLine {
	
	@Id
	@GeneratedValue
	private int id;
	private int quantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinTable(name = "product_orderline")
	private Product product;

	public OrderLine() {
	}

	public OrderLine(int quantity) {
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
