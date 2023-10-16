package com.devsu.client.application.service;

import com.devsu.client.application.port.IClientService;
import com.devsu.client.domain.dto.Client;
import com.devsu.client.domain.mapper.ClientDataMapper;
import com.devsu.client.infrastructure.entity.ClientEntity;
import com.devsu.client.infrastructure.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService implements IClientService {

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository, ClientDataMapper clientDataMapper) {
        this.clientRepository = clientRepository;
    }


    @Override
    public Flux<Client> findAll() throws InterruptedException {
        logger.info("Searching clients");
        Flux<ClientEntity> clientEntityFlux  = clientRepository.findAll();
        logger.info("Searching clients completed");
        return clientEntityFlux
                .map(ClientDataMapper::fromClientEntityToClient)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Client> getClientById(Long id) {
        logger.info("Searching client");
        Mono<ClientEntity> clientsEntity = clientRepository.findClientById(id);
        logger.info("Searching client completed");
        return clientsEntity.map(ClientDataMapper::fromClientEntityToClient)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Client> save(Client client) {
        logger.info("Saving client completed");
        Mono<ClientEntity> clientEntity = clientRepository.save(ClientDataMapper.fromClientToClientEntity(client));
        logger.info("Saved client");
        return clientEntity.map(ClientDataMapper::fromClientEntityToClient)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Client> update(Client client, Long id) {
        Mono<ClientEntity> clientMono = clientRepository.findById(id);

        return clientMono.flatMap((existingClient) -> {
            existingClient.setName(client.getName());
            existingClient.setPassword(client.getPassword());
            existingClient.setStatus(client.isStatus());
            existingClient.setAddres(client.getAddres());
            existingClient.setAge(client.getAge());
            existingClient.setGenre(client.getGenre());
            existingClient.setIdentification(client.getIdentification());
            existingClient.setPhone(client.getPhone());
            return clientRepository.save(existingClient);
        }).map((ClientDataMapper::fromClientEntityToClient));
    }


    @Override
    public Mono<Object> delete(Long id) {
        Mono<ClientEntity> clientMono = clientRepository.findById(id);
        return clientMono.flatMap(clientRepository::delete);
    }
}
