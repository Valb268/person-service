package telran.java51.person.service;

import java.util.List;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.PersonDto;

public interface PersonService {

	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	List<PersonDto> findByCity(String city);
	
	List<PersonDto> findByAges(Integer minAge, Integer maxAge);
	
	PersonDto updateName(Integer id, String newName);
	
	List<PersonDto> findByName(String name);

	PersonDto updateAddress(Integer id, AddressDto newAddress);

	PersonDto deletePerson(Integer id);
}
