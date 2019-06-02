package br.com.murilo.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.murilo.data.model.Person;
import br.com.murilo.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {

	public PersonVOV2 convertEntityToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setAddress(person.getAddress());
		vo.setBirthdate(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setGender(person.getGender());
		vo.setId(person.getId());
		vo.setLastName(person.getLastName());
		return vo ;
	}
	
	public Person convertVOToEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setGender(person.getGender());
		entity.setId(person.getId());
		entity.setLastName(person.getLastName());
		return entity;
	}
}
