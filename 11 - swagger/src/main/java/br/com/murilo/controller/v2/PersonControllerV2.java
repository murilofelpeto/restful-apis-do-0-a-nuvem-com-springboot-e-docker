package br.com.murilo.controller.v2;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.murilo.data.vo.v1.PersonVO;
import br.com.murilo.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Person endpoint", description = "This controller is responsible for person table", tags = { "Person V2"})
@RestController
@RequestMapping("/api/person/v2")
public class PersonControllerV2 {

	@Autowired
	private PersonService service;

	@ApiOperation(value = "Find all people recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<PersonVO> findAll() {
		List<PersonVO> persons = service.findAll();
		persons.stream()
				.forEach(p -> p.add(linkTo(methodOn(PersonControllerV2.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	@ApiOperation(value = "Find person by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO personVO = service.findById(id);
		personVO.add(linkTo(methodOn(PersonControllerV2.class).findById(id)).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Saves a person in database")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
		PersonVO personVO = service.create(person);
		personVO.add(linkTo(methodOn(PersonControllerV2.class).findById(personVO.getKey())).withSelfRel());
		return new ResponseEntity<>(personVO, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a person in database")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO personVO = service.update(person);
		personVO.add(linkTo(methodOn(PersonControllerV2.class).findById(personVO.getKey())).withSelfRel());
		return personVO;
	}

	@ApiOperation(value = "Delete a person in database")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
