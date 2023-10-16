package com.devsu.account.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Movement {

    private Long id;
    private LocalDateTime dateMovement;
    private String movementType;
    private Long valueMovement;
    private Long balance;
    private Long accountId;

    public Movement(){}

    public Movement(Long id, LocalDateTime dateMovement, String movementType, Long value_movement, Long balance, Long accountId) {
        this.id = id;
        this.dateMovement = dateMovement;
        this.movementType = movementType;
        this.valueMovement = value_movement;
        this.balance = balance;
        this.accountId = accountId;
    }
}
