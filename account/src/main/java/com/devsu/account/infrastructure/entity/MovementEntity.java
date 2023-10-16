package com.devsu.account.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Table(name = "movement")
@Getter
@Setter
@Builder
public class MovementEntity {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_movement")
    private LocalDateTime dateMovement;
    @Column(name = "movement_type")
    private String movementType;
    @Column(name = "value_movement")
    private Long valueMovement;
    private Long balance;
    @Column(name = "account_id")
    private Long accountId;

    public MovementEntity(){}

    public MovementEntity(Long id, LocalDateTime dateMovement, String movementType, Long valueMovement, Long balance, Long accountId) {
        this.id = id;
        this.dateMovement = dateMovement;
        this.movementType = movementType;
        this.valueMovement = valueMovement;
        this.balance = balance;
        this.accountId = accountId;
    }
}
