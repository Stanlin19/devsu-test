package com.devsu.client.domain.clientMapper;

import com.devsu.client.domain.dto.Client;
import com.devsu.client.infrastructure.entity.ClientEntity;

public class ClientDataMapper {
    public static Client fromClientEntityToClient(ClientEntity client){
        return Client.builder()
                .id(client.getId())
                .status(client.isStatus())
                .password(client.getPassword())
                .name(client.getName())
                .phone(client.getPhone())
                .addres(client.getAddres())
                .age(client.getAge())
                .genre(client.getGenre())
                .identification(client.getIdentification())
                .build();
    }

    public static ClientEntity fromClientToClientEntity(Client client){
        return ClientEntity.builder()
                .id(client.getId())
                .status(client.isStatus())
                .password(client.getPassword())
                .name(client.getName())
                .phone(client.getPhone())
                .addres(client.getAddres())
                .age(client.getAge())
                .genre(client.getGenre())
                .identification(client.getIdentification())
                .build();
    }
}
