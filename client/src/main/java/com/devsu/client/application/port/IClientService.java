package com.devsu.client.application.port;

import com.devsu.client.domain.dto.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IClientService {

    List<Client> findAll();
    Client getClientById(Long id);
    Client save(Client client);
    Client update(Client client, Long id);
    String delete(Long id);
}
