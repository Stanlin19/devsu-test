package com.devsu.account.application.service;

import com.devsu.account.application.port.IMovementService;
import com.devsu.account.domain.dto.Movement;
import com.devsu.account.domain.mapper.MovementDataMapper;
import com.devsu.account.infrastructure.entity.MovementEntity;
import com.devsu.account.infrastructure.repository.AccountRepository;
import com.devsu.account.infrastructure.repository.MovementReporitory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MovementService implements IMovementService {

    private static final Logger logger = LoggerFactory.getLogger(MovementService.class);

    private final MovementReporitory movementReporitory;

    public MovementService(MovementReporitory movementReporitory) {
        this.movementReporitory = movementReporitory;
    }

    @Override
    public Flux<Movement> findAll() throws InterruptedException {
        logger.info("Searching movements");
        Flux<MovementEntity> movementEntity  = movementReporitory.findAll();
        logger.info("Searching movements completed");
        return movementEntity
                .map(MovementDataMapper::fromMovementEntityToMovement)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Movement> save(Movement movement) {
        logger.info("Saving movement completed");
        movement.setDateMovement(LocalDateTime.now());
        Mono<MovementEntity> movementEntity = movementReporitory.save(MovementDataMapper.fromMovementToMovementEntity(movement));
        logger.info("Saved movement");
        return movementEntity.map(MovementDataMapper::fromMovementEntityToMovement)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Movement> update(Movement movement, Long id) {
        Mono<MovementEntity> movementEntity = movementReporitory.findById(id);

        return movementEntity.flatMap((existingMovement) -> {
            existingMovement.setMovementType(movement.getMovementType());
            existingMovement.setDateMovement(movement.getDateMovement());
            existingMovement.setValueMovement(movement.getValueMovement());
            existingMovement.setBalance(movement.getBalance());
            existingMovement.setAccountId(movement.getAccountId());
            existingMovement.setDateMovement(LocalDateTime.now());
            return movementReporitory.save(existingMovement);
        }).map((MovementDataMapper::fromMovementEntityToMovement));
    }

    @Override
    public Mono<Object> delete(Long id) {
        Mono<MovementEntity> movementEntity = movementReporitory.findById(id);
        return movementEntity.flatMap(movementReporitory::delete);
    }
}
