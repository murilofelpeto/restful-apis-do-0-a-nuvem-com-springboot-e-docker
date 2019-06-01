package br.com.murilo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.github.javafaker.Faker;

import br.com.murilo.model.Person;

//Spring cuida da injeção de dependencia
@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Murilo");
		person.setLastName("Felpeto");
		person.setAddress("São Paulo - SP - Brasil");
		person.setGender("Male");
		return person ;
	}
	
	public List<Person> findAll(){
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 10; i++) {
			Person person = mockPerson();			
			persons.add(person);
		}		
		return persons;
	}

	public Person create(Person person) {
		return person;
	}
	
	public Person update(Person person) {
		return person;
	}
	
	public void delete(String id) {
		
	}
	
	private Person mockPerson() {
		Faker faker = new Faker();
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName(faker.rickAndMorty().character());
		person.setLastName(faker.witcher().monster());
		person.setAddress(faker.address().streetAddress());
		person.setGender("Male");
		return person;
	}
}
