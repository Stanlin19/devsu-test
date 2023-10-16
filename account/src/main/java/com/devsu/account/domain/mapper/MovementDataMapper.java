package com.devsu.account.domain.mapper;

import com.devsu.account.domain.dto.Movement;
import com.devsu.account.infrastructure.entity.MovementEntity;
import org.springframework.stereotype.Component;

@Component
public class MovementDataMapper {

    public static Movement fromMovementEntityToMovement(MovementEntity movement){
        return Movement.builder()
                .id(movement.getId())
                .accountId(movement.getAccountId())
                .dateMovement(movement.getDateMovement())
                .movementType(movement.getMovementType())
                .balance(movement.getBalance())
                .valueMovement(movement.getValueMovement())
                .build();
    }

    public static MovementEntity fromMovementToMovementEntity(Movement movement){
        return MovementEntity.builder()
                .id(movement.getId())
                .accountId(movement.getAccountId())
                .dateMovement(movement.getDateMovement())
                .movementType(movement.getMovementType())
                .balance(movement.getBalance())
                .valueMovement(movement.getValueMovement())
                .build();
    }
}
