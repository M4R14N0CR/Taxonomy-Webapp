package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.*;
import org.springframework.data.repository.CrudRepository;

//Repositorio de las instituciones
public interface institutionRepository extends CrudRepository<institution, Integer>{

    //Metodo para contar por id
    public long countById(Integer id);
}