package com.devsu.client.application.service;

import com.devsu.client.application.port.IClientService;
import com.devsu.client.domain.clientMapper.ClientDataMapper;
import com.devsu.client.domain.dto.Client;
import com.devsu.client.infrastructure.entity.ClientEntity;
import com.devsu.client.infrastructure.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> findAll() {
        logger.info("Searching clients");
        List<Client> clients = clientRepository.findAll()
                .stream().map(ClientDataMapper::fromClientEntityToClient)
                .toList();
        logger.info("Searching clients completed");
        return clients;
    }

    @Override
    public Client getClientById(Long id) {
        logger.info("Searching client");
        ClientEntity clientEntity = clientRepository.findClientById(id);
        logger.info("Searching client completed");
        return ClientDataMapper.fromClientEntityToClient(clientEntity);
    }

    @Override
    public Client save(Client client) {
        logger.info("Saving client completed");
        ClientEntity clientEntity = clientRepository.save(ClientDataMapper.fromClientToClientEntity(client));
        logger.info("Saved client");
        return ClientDataMapper.fromClientEntityToClient(clientEntity);
    }

    @Override
    public Client update(Client client, Long id) {
        logger.info("Updating client completed");
        ClientEntity clientEntity = clientRepository.save(ClientDataMapper.fromClientToClientEntity(client));
        logger.info("Updating client");
        return ClientDataMapper.fromClientEntityToClient(clientEntity);
    }

    @Override
    public String delete(Long id) {
        Optional<ClientEntity> client = clientRepository.findById(id);
        if(client.isEmpty()){
            return "Client with id "+id+" was not found";
        } else {
            clientRepository.deleteById(id);
            return "Client deleted";
        }
    }
}
