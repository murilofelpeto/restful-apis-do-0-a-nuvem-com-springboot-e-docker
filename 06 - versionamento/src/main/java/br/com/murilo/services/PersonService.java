package br.com.murilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.murilo.converter.DozerConverter;
import br.com.murilo.converter.custom.PersonConverter;
import br.com.murilo.data.model.Person;
import br.com.murilo.data.vo.PersonVO;
import br.com.murilo.data.vo.v2.PersonVOV2;
import br.com.murilo.exception.ResourceNotFoundException;
import br.com.murilo.repository.PersonRepository;

//Spring cuida da injeção de dependencia
@Service
public class PersonService {

	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;

	private static final String MESSAGE = "No records found for this id";

	public PersonVO findById(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public List<PersonVO> findAll() {
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		Person entity = converter.convertVOToEntity(person);
		PersonVOV2 vo = converter.convertEntityToVO(repository.save(entity));
		return vo;
	}

	public PersonVO update(PersonVO p) {
		Person entity = repository.findById(p.getId()).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void delete(Long id) {
		Person entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		repository.delete(entity);
	}
}
