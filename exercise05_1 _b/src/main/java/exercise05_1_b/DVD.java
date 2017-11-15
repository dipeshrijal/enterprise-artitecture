package exercise05_1_b;

import javax.persistence.Entity;

@Entity
public class DVD extends Product {

	private String genre;

	public DVD() {
		super();
	}

	public DVD(String genre, String name, String description) {
		super(name, description);
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

}
