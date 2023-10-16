package com.devsu.client.infrastructure.repository;

import com.devsu.client.infrastructure.entity.ClientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ClientRepository extends R2dbcRepository<ClientEntity, Long> {

    Mono<ClientEntity> findClientById(Long id);

}
