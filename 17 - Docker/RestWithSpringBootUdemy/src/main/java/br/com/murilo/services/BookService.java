package br.com.murilo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<BookVO> findAll(Pageable pageable){
		Page<Book> page = repository.findAll(pageable);
		return page.map(this::convertToBookVO);
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
	
	private BookVO convertToBookVO(Book entity) {
		return DozerConverter.parseObject(entity, BookVO.class);
	}
}
