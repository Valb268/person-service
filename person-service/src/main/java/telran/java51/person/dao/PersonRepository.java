package telran.java51.person.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {

	Stream<Person> findByAddressCityIgnoreCase(String city);
	
	Stream<Person> findByBirthDateBetween(LocalDate fromDate, LocalDate toDate);

	Stream<Person> findByNameIgnoreCase(String name);
	
	@Query("select new telran.java51.person.dto.CityPopulationDto(p.address.city, count(p)) "
			+ "from Person p group by p.address.city order by count(p) desc")
	List<CityPopulationDto> getCitiesPopulation();

	@Query("select c from Child c")
	Stream<Child> findAllChildren();

	@Query("select e from Employee e where e.salary between ?1 and ?2")
	Stream<Employee> findEmployeesBySalaryBetween(int minSalary,
			int maxSalary);

}
