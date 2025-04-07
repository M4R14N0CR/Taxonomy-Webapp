package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.reino;
import org.springframework.data.repository.CrudRepository;

//Repositorio de las familias
public interface reinoRepository extends CrudRepository<reino, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
