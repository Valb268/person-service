package telran.java51.person.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.AddressDto;
import telran.java51.person.dto.ChildDto;
import telran.java51.person.dto.CityPopulationDto;
import telran.java51.person.dto.EmployeeDto;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.exceptions.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Child;
import telran.java51.person.model.Employee;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;
	final PersonModelDtoMapper mapper;

	@Transactional
	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		
		// not universal code
//		if (personDto instanceof ChildDto childDto) {
//			personRepository.save(modelMapper.map(childDto, Child.class));
//		} else if (personDto instanceof EmployeeDto employeeDto) {
//			personRepository.save(modelMapper.map(employeeDto, Employee.class));
//		} else {
//			personRepository.save(modelMapper.map(personDto, Person.class));
//		}
		personRepository.save(mapper.mapToModel(personDto));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		return mapper.mapToDto(person);
	}

	// not universal code
//	private PersonDto personDtoMapper(Person person) {
//		if (person instanceof Child child) {
//			return modelMapper.map(child, ChildDto.class);
//		}
//		if (person instanceof Employee employee) {
//			return modelMapper.map(employee, EmployeeDto.class);
//		}
//		return modelMapper.map(person, PersonDto.class);
//	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByCity(String city) {
		return personRepository.findByAddressCityIgnoreCase(city)
				.map(mapper::mapToDto)
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByAges(Integer minAge, Integer maxAge) {
		LocalDate now = LocalDate.now();
		LocalDate fromDate = now.minusYears(maxAge);
		LocalDate toDate = now.minusYears(minAge);
		return personRepository.findByBirthDateBetween(fromDate, toDate)
				.map(mapper::mapToDto)
				.toList();
	}

	@Transactional
	@Override
	public PersonDto updateName(Integer id, String newName) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		person.setName(newName);
		// person = personRepository.save(person);
		return mapper.mapToDto(person);
	}

	@Transactional(readOnly = true)
	@Override
	public List<PersonDto> findByName(String name) {
		return personRepository.findByNameIgnoreCase(name)
				.map(mapper::mapToDto)
				.toList();
	}

	@Transactional
	@Override
	public PersonDto updateAddress(Integer id, AddressDto newAddress) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		person.setAddress(modelMapper.map(newAddress, Address.class));
		// В силу транзакционности при апдейте не нужно сохранять
		// Транзакционность автоматически это сделает
		// person = personRepository.save(person);
		return mapper.mapToDto(person);
	}

	@Transactional
	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id)
				.orElseThrow(PersonNotFoundException::new);
		personRepository.deleteById(id);
		return mapper.mapToDto(person);
	}

	@Override
	public Iterable<CityPopulationDto> getCityPopulation() {
		return personRepository.getCitiesPopulation();
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		if (personRepository.count() == 0) {
			Person person = new Person(1000, "John", LocalDate.of(1985, 3, 14),
					new Address("Tel Aviv", "Ben Gvirol", 81));
			Child child = new Child(2000, "Mosche", LocalDate.of(2018, 5, 11),
					new Address("Ashkelon", "Bar Kohba", 21), "Shalom");
			Employee employee = new Employee(3000, "Sarah",
					LocalDate.of(1995, 11, 23),
					new Address("Rehovot", "Herzl", 7), "Motorola", 20_000);
			personRepository.save(person);
			personRepository.save(child);
			personRepository.save(employee);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<ChildDto> findAllChildren() {
		return personRepository.findAllChildrenBy()
				.map(c -> modelMapper.map(c, ChildDto.class))
				.toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<EmployeeDto> findEmployeesBySalaryBetween(int minSalary,
			int maxSalary) {
		
		return personRepository.findEmployeesBySalaryBetween(minSalary, maxSalary)
				.map(e -> modelMapper.map(e, EmployeeDto.class))
				.toList();
	}

}
