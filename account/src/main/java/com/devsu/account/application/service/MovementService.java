package com.devsu.account.application.service;

import com.devsu.account.application.port.IMovementService;
import com.devsu.account.domain.dto.Movement;
import com.devsu.account.domain.mapper.MovementDataMapper;
import com.devsu.account.infrastructure.entity.AccountEntity;
import com.devsu.account.infrastructure.entity.MovementEntity;
import com.devsu.account.infrastructure.repository.MovementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Async
public class MovementService implements IMovementService {

    private static final Logger logger = LoggerFactory.getLogger(MovementService.class);
    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository) {
        this.movementRepository = movementRepository;
    }

    @Override
    public CompletableFuture<List<Movement>> findAll() {
        logger.info("Searching movements");
        List<Movement> movements = movementRepository.findAll()
                .stream().map(MovementDataMapper::fromMovementEntityToMovement)
                .toList();
        logger.info("Searching movements completed");
        return CompletableFuture.completedFuture(movements);
    }

    @Override
    public CompletableFuture<Movement> save(Movement movement) {
        logger.info("Saving movement");
        MovementEntity movementEntity = movementRepository.save(MovementDataMapper.fromMovementToMovementEntity(movement));
        logger.info("Saved movement completed");
        return CompletableFuture.completedFuture(MovementDataMapper.fromMovementEntityToMovement(movementEntity));
    }

    @Override
    public CompletableFuture<Movement> update(Movement movement, Long id) {
        logger.info("Updating movement");
        MovementEntity movementEntity = movementRepository.save(MovementDataMapper.fromMovementToMovementEntity(movement));
        logger.info("Updated movement completed");
        return CompletableFuture.completedFuture(MovementDataMapper.fromMovementEntityToMovement(movementEntity));
    }

    @Override
    public CompletableFuture<String> delete(Long id) {
        Optional<MovementEntity> movement = movementRepository.findById(id);
        if(movement.isEmpty()){
            return CompletableFuture.completedFuture("Movement with id "+id+" was not found");
        } else {
            movementRepository.deleteById(id);
            return CompletableFuture.completedFuture("Movement deleted");
        }
    }

    public CompletableFuture<MovementEntity> getLastMovementByAccountId(Long id){
        return CompletableFuture.completedFuture(movementRepository.findMovementByAccountIdOrderByIdDesc(id).get(0));
    }
}
