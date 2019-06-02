package br.com.murilo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.murilo.data.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
