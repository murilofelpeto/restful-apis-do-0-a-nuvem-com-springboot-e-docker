package br.com.murilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.murilo.exception.ResourceNotFoundException;
import br.com.murilo.model.Person;
import br.com.murilo.repository.PersonRepository;

//Spring cuida da injeção de dependencia
@Service
public class PersonService {

	@Autowired
	PersonRepository repository;

	private static final String MESSAGE = "No records found for this id";

	public Person findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
	}

	public List<Person> findAll() {
		return repository.findAll();
	}

	public Person create(Person person) {
		repository.save(person);
		return person;
	}

	public Person update(Person p) {
		Person entity = repository.findById(p.getId()).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		return repository.save(entity);
	}

	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		repository.delete(entity);
	}
}
