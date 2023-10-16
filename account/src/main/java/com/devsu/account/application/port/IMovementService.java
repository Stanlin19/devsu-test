package com.devsu.account.application.port;

import com.devsu.account.domain.dto.Movement;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IMovementService {

    Flux<Movement> findAll() throws InterruptedException;
    Mono<Movement> save(Movement movement);
    Mono<Movement> update(Movement movement, Long id);
    Mono<Object> delete(Long id);
}
