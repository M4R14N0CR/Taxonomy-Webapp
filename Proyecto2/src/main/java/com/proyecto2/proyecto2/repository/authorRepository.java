package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.Author;
import org.springframework.data.repository.CrudRepository;

//Repositorio de los autores
public interface authorRepository extends CrudRepository<Author, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}