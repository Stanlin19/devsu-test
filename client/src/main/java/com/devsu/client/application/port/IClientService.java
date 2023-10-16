package com.devsu.client.application.port;

import com.devsu.client.domain.dto.Client;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IClientService {

    Flux<Client> findAll() throws InterruptedException;
    Mono<Client> getClientById(Long id);
    Mono<Client> save(Client client);
    Mono<Client> update(Client client, Long id);
    Mono<Object> delete(Long id);
}
