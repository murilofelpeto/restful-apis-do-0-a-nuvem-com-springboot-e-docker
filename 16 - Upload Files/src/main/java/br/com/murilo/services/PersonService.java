package br.com.murilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.murilo.converter.DozerConverter;
import br.com.murilo.data.model.Person;
import br.com.murilo.data.vo.PersonVO;
import br.com.murilo.exception.ResourceNotFoundException;
import br.com.murilo.repository.PersonRepository;

//Spring cuida da injeção de dependencia
@Service
public class PersonService {

	@Autowired
	PersonRepository repository;

	private static final String MESSAGE = "No records found for this id";

	public PersonVO findById(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		Page<Person> page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}
	
	public Page<PersonVO> findPersonByName(Pageable pageable, String firstName) {
		Page<Person> page = repository.findByFirstNameContaining(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}

	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		repository.delete(entity);
	}
	
	//Essa anotação garante que o DB continue consistente ao ser manipulado (ACID)
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
}
