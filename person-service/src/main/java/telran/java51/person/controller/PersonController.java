package telran.java51.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.service.PersonService;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

	final PersonService personService;
	
	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findByCity(@PathVariable String city) {
		return personService.findByCity(city);
	}
	
	@GetMapping("/ages/{minAge}/{maxAge}")
	public Iterable<PersonDto> findByAges(@PathVariable Integer minAge, @PathVariable Integer maxAge) {
		return personService.findByAges(minAge, maxAge);
	}
	
	@PutMapping("/{id}/name/{newName}")
	public PersonDto updateName(@PathVariable Integer id, @PathVariable String newName) {
		return personService.updateName(id, newName);
	}
	
	@GetMapping("/name/{name}")
	public Iterable<PersonDto> findByName(@PathVariable String name) {
		return personService.findByName(name);
	}
	
	@PutMapping("/{id}/address/")
	public PersonDto updateAddress(@PathVariable Integer id, @RequestBody AddressDto newAddress) {
		return personService.updateAddress(id, newAddress);
	}
	
	@DeleteMapping("/{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
	@GetMapping("/population/city")
	public Iterable<CityPopulationDto> getCitiesPopulation() {
		return personService.getCityPopulation();
	}
	
}
