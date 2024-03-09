package telran.java51.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2812010942738675155L;
	@Id
	Integer id;
	@Setter
	String name;
	LocalDate birthDate;
	@Setter
//	@Embedded
	Address address;
}
