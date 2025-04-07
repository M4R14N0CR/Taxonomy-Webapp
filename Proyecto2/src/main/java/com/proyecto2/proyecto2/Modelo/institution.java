package com.proyecto2.proyecto2.Modelo;
import jakarta.persistence.*;

@Entity
@Table(name="institution")
@PrimaryKeyJoinColumn(name="id")
public class institution extends owner{
//Clase de instituciones

    //Id de la institucion
    @MapsId
    private Integer id;

    //WebSite de la institucion
    @Column(nullable = false, length = 50, name = "website")
    private String website;

    //Getters y setters
    @Override
    public void setId(Integer id){
        this.id = id;
    }

    @Override
    public Integer getId(){
        return this.id;
    }

    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }

    //Metodo toString de la imagen
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", lastname='" + website + '\'' +
                '}';
    }

}