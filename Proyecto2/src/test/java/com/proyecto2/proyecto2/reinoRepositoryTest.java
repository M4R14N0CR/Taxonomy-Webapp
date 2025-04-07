package com.proyecto2.proyecto2;


import com.proyecto2.proyecto2.Modelo.reino;
import com.proyecto2.proyecto2.repository.reinoRepository;
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
public class reinoRepositoryTest {

    @Autowired
    private reinoRepository repo;

    @Test
    public void testaddNewreino(){

        reino reino = new reino();
        reino.setScientific_name("Reinitus");
        reino.setPublication_year("18/11/2002");
        reino.setTaxon_ancestor(1);
        reino.setAuthor_name("Brandon");

        reino savedreino = repo.save(reino);

        Assertions.assertThat(savedreino).isNotNull();
        Assertions.assertThat(savedreino.getId()).isGreaterThan(0);
    }


    @Test
    public void testListAllreinos(){
        Iterable<reino> reinos = repo.findAll();
        Assertions.assertThat(reinos).hasSizeGreaterThan(0);
        for(reino reino : reinos){
            System.out.println(reino);
        }
    }

    @Test
    public void testGetreino(){

        Integer reinoId =9;
        Optional<reino> optionalreino= repo.findById(reinoId);
        Assertions.assertThat(optionalreino).isPresent();

        System.out.println(optionalreino.get());
    }

    @Test
    public void testDeletereino(){
        Integer reinoId = 8;
        repo.deleteById(reinoId);

        Optional<reino> optionalreino= repo.findById(reinoId);
        Assertions.assertThat(optionalreino).isNotPresent();


    }


}