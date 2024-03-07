package telran.java51.person.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Child extends Person {

	/**
	 * 
	 */
	private static final long serialVersionUID = 132784094816046692L;

	String kindergarten;

	public Child(Integer id, String name, LocalDate birthDate, Address address,
			String kindergarten) {
		super(id, name, birthDate, address);
		this.kindergarten = kindergarten;
	}
	
	
	
	
}
