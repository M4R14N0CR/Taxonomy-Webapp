package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.familia;
import org.springframework.data.repository.CrudRepository;

//Repositorio de las familias
public interface familiaRepository extends CrudRepository<familia, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
