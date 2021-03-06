package br.com.murilo.controller;

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

import br.com.murilo.data.vo.BookVO;
import br.com.murilo.services.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Books endpoint", description = "This controller is responsible for books table", tags = { "Book" })
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

	@Autowired
	private BookService service;

	@ApiOperation(value = "Find all books recorded")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> books = service.findAll();
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	@ApiOperation(value = "Find books by ID")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable(value = "id") Long id) {
		BookVO book = service.findById(id);
		book.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return book;
	}

	@ApiOperation(value = "Save a book at the database")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<BookVO> create(@RequestBody BookVO book) {
		BookVO entity = service.create(book);
		entity.add(linkTo(methodOn(BookController.class).findById(entity.getKey())).withSelfRel());
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Update a book at the database")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO entity = service.update(book);
		entity.add(linkTo(methodOn(BookController.class).findById(entity.getKey())).withSelfRel());
		return entity;
	}

	@ApiOperation(value = "Delete a book at the database")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
