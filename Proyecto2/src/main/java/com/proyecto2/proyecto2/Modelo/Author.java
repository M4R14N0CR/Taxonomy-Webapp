package com.proyecto2.proyecto2.Modelo;
import jakarta.persistence.*;

@Entity
@Table(name="author")
@PrimaryKeyJoinColumn(name="id")
public class Author extends owner{
//Clase de los autores de las imagenes

    //Id de los autores
    @MapsId
    private Integer id;

    //Apellidos de los autores
    @Column(nullable = false, length = 50, name = "lastname")
    private String lastname;

    //Setter y getters
    @Override
    public void setId(Integer id){
        this.id = id;
    }

    @Override
    public Integer getId(){
        return this.id;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                '}';
    }

}