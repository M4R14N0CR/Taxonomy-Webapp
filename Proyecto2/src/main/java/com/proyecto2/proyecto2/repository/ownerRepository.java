package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.owner;
import org.springframework.data.repository.CrudRepository;

//Repositorio de las familias
public interface ownerRepository extends CrudRepository<owner, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
