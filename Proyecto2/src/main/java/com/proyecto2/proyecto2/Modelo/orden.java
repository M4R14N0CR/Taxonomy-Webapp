package com.proyecto2.proyecto2.Modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="orden")
@PrimaryKeyJoinColumn(name="id")
public class orden extends taxon{
//Clase de los ordenes

    //Id de los ordenes
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
        return "orden{" +
                "id=" + id +
                '}';
    }
}

