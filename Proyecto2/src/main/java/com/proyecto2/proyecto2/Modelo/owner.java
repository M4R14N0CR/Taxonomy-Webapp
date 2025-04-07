package com.proyecto2.proyecto2.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "owners")
@Inheritance(strategy = InheritanceType.JOINED)
public class owner {
//Clase de los dueños

    //Id de los dueños
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Nombre de los dueños
    @Column(nullable = false, length = 50, name = "name")
    private String name;

    //Email de los dueños
    @Column(length = 25, nullable = false, name = "Email")
    private String Email;

    //Pais de los dueños
    @Column(length = 15, nullable = false, name = "country")
    private String country;

    //Telefono de los dueños
    @Column(length = 10,nullable = false, name = "phone")
    private int phone;

    //Setters y getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getCountry() {
        return country;
    }

    public int getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    //Metodo toString
    @Override
    public String toString() {
        return "owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                ", country='" + country + '\'' +
                ", phone=" + phone +
                '}';
    }
}

