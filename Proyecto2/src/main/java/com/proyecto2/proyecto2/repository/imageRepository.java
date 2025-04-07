package com.proyecto2.proyecto2.repository;

import com.proyecto2.proyecto2.Modelo.image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//Repositorio de las imagenes
@Repository
public interface imageRepository extends JpaRepository<image, Integer> {

    //Metodo para contar por id
    public Long countById(Integer id);


    //Query que permite hacer las busquedas
    @Query(value = "SELECT  DISTINCT i.id, i.date,i.description,i.license,i.path,i.author_id,i.owner_id,i.clase_id,i.familia_id,i.filo_id,i.genero_id,i.orden_id,i.reino_id FROM image i INNER JOIN image_keywords k ON i.id = k.image_id INNER JOIN owners o ON o.id = i.owner_id INNER JOIN author a ON a.id = i.author_id INNER JOIN taxon t ON t.id = i.reino_id OR t.id = i.filo_id OR i.clase_id = t.id OR i.familia_id = t.id OR i.genero_id = t.id OR i.orden_id = t.id WHERE k.keywords LIKE %:clave% OR o.name LIKE %:clave% OR a.lastname LIKE %:clave% OR t.scientific_name LIKE %:clave%", nativeQuery = true)
    List<image> busqueda(@Param("clave") String clave);
}

