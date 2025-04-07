package com.proyecto2.proyecto2.Modelo;


import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Entity
@Table(name = "image")
public class image {
//Clase de las imagenes

    //Id de las imagenes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Fecha de creacion de la imagen
    @Column(name = "date")
    private String date;

    //La direccion de la imagen
    @Column(name = "path")
    private String path;

    @Transient
    private MultipartFile nameimage;

    //La descripcion de la imagen
    @Column(name = "description")
    private String description;

    //Las keywords de la imagen
    @ElementCollection
    private Set<String> keywords;

    //La liencia de la imagen
    @Enumerated(EnumType.STRING)
    private License license;

    //El dueño de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private owner ownerId;

    //El autor de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    //El reino de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Reino_id", nullable = false)
    private reino Reino;

    //El filo de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Filo_id", nullable = false)
    private filo Filo;

    //La clase de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Clase_id", nullable = false)
    private clase Clase;

    //El orden de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Orden_id", nullable = false)
    private orden Orden;

    //La familia de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Familia_id", nullable = false)
    private familia Familia;

    //El genero de la imagen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Genero_id", nullable = false)
    private genero Genero;


    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }

    public owner getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(owner ownerId) {
        this.ownerId = ownerId;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MultipartFile getNameimage() {
        return nameimage;
    }

    public void setNameimage(MultipartFile nameimage) {
        this.nameimage = nameimage;
    }

    public reino getReino() {
        return Reino;
    }

    public void setReino(reino reino) {
        Reino = reino;
    }

    public filo getFilo() {
        return Filo;
    }

    public void setFilo(filo filo) {
        Filo = filo;
    }

    public clase getClase() {
        return Clase;
    }

    public void setClase(clase clase) {
        Clase = clase;
    }

    public orden getOrden() {
        return Orden;
    }

    public void setOrden(orden orden) {
        Orden = orden;
    }

    public familia getFamilia() {
        return Familia;
    }

    public void setFamilia(familia familia) {
        Familia = familia;
    }

    public genero getGenero() {
        return Genero;
    }

    public void setGenero(genero genero) {
        Genero = genero;
    }
}
