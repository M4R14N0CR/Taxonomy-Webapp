package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.filo;
import org.springframework.data.repository.CrudRepository;

//Repositorio de los filos
public interface filoRepository extends CrudRepository<filo, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);

}
