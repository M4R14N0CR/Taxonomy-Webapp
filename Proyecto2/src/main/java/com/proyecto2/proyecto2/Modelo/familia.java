package com.proyecto2.proyecto2.Modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="familia")
@PrimaryKeyJoinColumn(name="id")
public class familia extends taxon{
//Clase de las familias

    //Id de las familias
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
        return "familia{" +
                "id=" + id +
                '}';
    }
}

