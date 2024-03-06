package telran.java51.person.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.exceptions.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findByCity(String city) {
		List<Person> persons = personRepository
				.findByAddressCityIgnoreCase(city);
		return persons.stream().map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public List<PersonDto> findByAges(Integer minAge, Integer maxAge) {
		LocalDate now = LocalDate.now();
		LocalDate fromDate = now.minusYears(maxAge);
		LocalDate toDate = now.minusYears(minAge);
		List<Person> persons = personRepository.findByBirthDateBetween(fromDate,
				toDate);

		return persons.stream().map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public PersonDto updateName(Integer id, String newName) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		person = personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findByName(String name) {
		List<Person> persons = personRepository.findByNameIgnoreCase(name);
		return persons.stream().map(p -> modelMapper.map(p, PersonDto.class))
				.toList();
	}

	@Override
	public PersonDto updateAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		person.setAddress(modelMapper.map(newAddress, Address.class));
		person = personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		personRepository.deleteById(id);
		return modelMapper.map(person, PersonDto.class);
	}

}
