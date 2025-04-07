package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.clase;
import org.springframework.data.repository.CrudRepository;

//Repositorio de las clases
public interface claseRepository extends CrudRepository<clase, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
