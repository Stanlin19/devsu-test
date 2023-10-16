package com.devsu.client.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends Person{
    private Long id;
    @NotBlank(message = "It should not be blank")
    @NotEmpty(message = "It should not be empty")
    @NotNull(message = "It should not be null")
    private String password;
    private boolean status;

    public Client(){}

    public Client(Long id, String password, boolean status) {
        this.id = id;
        this.password = password;
        this.status = status;
    }

    @Builder
    public Client(String name, String genre, int age, String identification, String addres, String phone, Long id,
                  String password, boolean status) {
        super(name, genre, age, identification, addres, phone);
        this.id = id;
        this.password = password;
        this.status = status;
    }
}
