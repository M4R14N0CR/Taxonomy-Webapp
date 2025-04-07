package com.proyecto2.proyecto2.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name="reino")
@PrimaryKeyJoinColumn(name="id")
public class reino extends taxon{
//Clase de los reinos

    //Id del reino
    @MapsId
    private Integer id;

    //Getters y setters
    @Override
    public void setId(Integer id){
        this.id = id;
    }

    @Override
    public Integer getId(){
        return this.id;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "reino{" +
                "id=" + id +
                '}';
    }
}

