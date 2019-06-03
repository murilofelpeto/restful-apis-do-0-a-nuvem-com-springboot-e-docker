package br.com.murilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.murilo.data.model.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
