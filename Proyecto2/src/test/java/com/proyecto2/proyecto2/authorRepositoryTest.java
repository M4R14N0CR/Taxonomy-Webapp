package com.proyecto2.proyecto2;


import com.proyecto2.proyecto2.Modelo.Author;
import com.proyecto2.proyecto2.repository.authorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class authorRepositoryTest {

    @Autowired private authorRepository repo;

    @Test
    public void testaddNewAuthor(){
        Author author = new Author();
        author.setName("Nano");
        author.setLastname("Moconitrillo");
        author.setEmail("nano@gmail.com");
        author.setCountry("cerote");
        author.setPhone(85676948);

        Author savedAuthor = repo.save(author);

        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAllAuthors(){
        Iterable<Author> authors = repo.findAll();
        Assertions.assertThat(authors).hasSizeGreaterThan(0);
        for(Author author : authors){
            System.out.println(author);
        }
    }

    @Test
    public void testUpdateAuthor(){

        Integer AuthorId =8;
        Optional<Author> optionalAuthor= repo.findById(AuthorId);
        Author author = optionalAuthor.get();

        author.setName("Joseph");
        repo.save(author);

        Author updatedAuthor = repo.findById(AuthorId).get();
        Assertions.assertThat(updatedAuthor.getName()).isEqualTo("Joseph");
    }

    @Test
    public void testGetAuthor(){

        Integer AuthorId =9;
        Optional<Author> optionalAuthor= repo.findById(AuthorId);
        Assertions.assertThat(optionalAuthor).isPresent();

        System.out.println(optionalAuthor.get());
    }

    @Test
    public void testDeleteAuthor(){
        Integer AuthorId = 8;
        repo.deleteById(AuthorId);

        Optional<Author> optionalAuthor= repo.findById(AuthorId);
        Assertions.assertThat(optionalAuthor).isNotPresent();


    }


}