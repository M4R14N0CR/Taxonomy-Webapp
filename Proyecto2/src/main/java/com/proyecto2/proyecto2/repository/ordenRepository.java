package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.orden;
import org.springframework.data.repository.CrudRepository;

//Repositorio de los ordenens
public interface ordenRepository extends CrudRepository<orden, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
