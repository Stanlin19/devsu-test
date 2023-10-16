package com.devsu.client.infrastructure.entity;

import com.devsu.client.domain.dto.Person;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "client")
@Inheritance(strategy= InheritanceType.JOINED)
@ToString
public class ClientEntity extends Person {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String password;
    private boolean status;

    public ClientEntity(){}

    public ClientEntity(Long id, String password, boolean status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    @Builder
    public ClientEntity(String name, String genre, int age, String identification, String addres, String phone,
                        Long id, String password, boolean status) {
        super(name, genre, age, identification, addres, phone);
        this.id = id;
        this.password = password;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
