package telran.java51.person.service;

import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;

public interface PersonService {

	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	Iterable<PersonDto> findByCity(String city);
	
	Iterable<PersonDto> findByAges(Integer minAge, Integer maxAge);
	
	PersonDto updateName(Integer id, String newName);
	
	Iterable<PersonDto> findByName(String name);

	PersonDto updateAddress(Integer id, AddressDto newAddress);

	PersonDto deletePerson(Integer id);
	
	Iterable<CityPopulationDto> getCityPopulation();
}
