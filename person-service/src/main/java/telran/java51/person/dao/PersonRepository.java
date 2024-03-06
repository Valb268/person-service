package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	List<Person> findByAddressCityIgnoreCase(String city);
	
	List<Person> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);

	List<Person> findByNameIgnoreCase(String name);

}
