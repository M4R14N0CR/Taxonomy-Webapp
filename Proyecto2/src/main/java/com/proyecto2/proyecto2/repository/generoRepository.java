package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.genero;
import org.springframework.data.repository.CrudRepository;

//Repositorio de los generos
public interface generoRepository extends CrudRepository<genero, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
