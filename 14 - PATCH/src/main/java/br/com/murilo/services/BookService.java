package br.com.murilo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.murilo.converter.DozerConverter;
import br.com.murilo.data.model.Book;
import br.com.murilo.data.vo.BookVO;
import br.com.murilo.exception.ResourceNotFoundException;
import br.com.murilo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	private final String MESSAGE = "No records found for this id";
	
	public BookVO findById(Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		return DozerConverter.parseObject(book, BookVO.class);
	}
	
	public List<BookVO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), BookVO.class);
	}
	
	public BookVO create(BookVO book) {
		Book entity = DozerConverter.parseObject(book, Book.class);
		BookVO vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
		return vo;
	}
	
	public BookVO update(BookVO book) {
		Book entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		return DozerConverter.parseObject(repository.save(entity), BookVO.class);
	}
	
	public void delete(Long id) {
		Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
		repository.delete(book);
	}
}
