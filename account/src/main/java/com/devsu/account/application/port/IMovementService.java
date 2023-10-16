package com.devsu.account.application.port;

import com.devsu.account.domain.dto.Movement;
import com.devsu.account.infrastructure.entity.MovementEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface IMovementService {

    CompletableFuture<List<Movement>> findAll();
    CompletableFuture<Movement> save(Movement movement);
    CompletableFuture<Movement> update(Movement movement, Long id);
    CompletableFuture<String> delete(Long id);
    CompletableFuture<MovementEntity> getLastMovementByAccountId(Long id);
}
