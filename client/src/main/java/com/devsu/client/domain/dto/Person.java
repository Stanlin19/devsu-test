package com.devsu.client.domain.dto;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@MappedSuperclass
public class Person {
    @NotBlank(message = "It should not be blank")
    @NotEmpty(message = "It should not be empty")
    @NotNull(message = "It should not be null")
    private String name;
    private String genre;
    private int age;
    private String identification;
    @NotBlank(message = "It should not be blank")
    @NotEmpty(message = "It should not be empty")
    @NotNull(message = "It should not be null")
    private String addres;
    @NotBlank(message = "It should not be blank")
    @NotEmpty(message = "It should not be empty")
    @NotNull(message = "It should not be null")
    private String phone;

    public Person(){}

    public Person(String name, String genre, int age, String identification, String addres, String phone) {
        this.name = name;
        this.genre = genre;
        this.age = age;
        this.identification = identification;
        this.addres = addres;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
