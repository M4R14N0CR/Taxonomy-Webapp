package com.proyecto2.proyecto2.Modelo;
import jakarta.persistence.*;


@Entity
@Table(name = "taxon")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class taxon {
//Clase de los taxones

    //Id de los taxones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Nombre cientifico del taxon
    @Column(nullable = false, length = 50, name = "scientific_name")
    private String scientific_name;

    //Nombre del autor del taxon
    @Column(nullable = false, length = 50, name = "author_name")
    private String author_name;

    //Año de publicacion del taxon
    @Column(length = 15, nullable = false, name = "publication_year")
    private String publication_year;

    //Id del ancestro del taxon
    @Column(length = 10,nullable = false, name = "taxon_ancestor")
    private int taxon_ancestor;


    //Setters y getters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public String getScientific_name() {
        return scientific_name;
    }
    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }


    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author) {
        this.author_name = author;
    }


    public String getPublication_year() {
        return publication_year;
    }
    public void setPublication_year(String publication_year) {
        this.publication_year = publication_year;
    }


    public int getTaxon_ancestor() {
        return taxon_ancestor;
    }
    public void setTaxon_ancestor(int taxon_ancestor) {
        this.taxon_ancestor = taxon_ancestor;
    }

    //Metodo toString
    public abstract String toString();
}

