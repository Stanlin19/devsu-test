package com.devsu.account.application.facade;

import com.devsu.account.application.port.IAcccountService;
import com.devsu.account.application.port.IMovementService;
import com.devsu.account.application.service.MovementService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.Movement;
import com.devsu.account.domain.dto.response.AccountResponse;
import com.devsu.account.infrastructure.entity.MovementEntity;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class RegisterMovement implements IRegisterMovement{

    private static final Logger logger = LoggerFactory.getLogger(MovementService.class);

    private final IAcccountService acccountService;
    private final IMovementService movementService;

    public RegisterMovement(IAcccountService acccountService, IMovementService movementService) {
        this.acccountService = acccountService;
        this.movementService = movementService;
    }

    @Override
    @Transactional
    public CompletableFuture<AccountResponse<Account>> registerMovement(Movement movement) throws ExecutionException,
            InterruptedException {
        Account accountEntity = acccountService.getAccountById(movement.getAccountId()).get().getObject();
        MovementEntity movementEntity = movementService.getLastMovementByAccountId(movement.getAccountId()).get();
        Long move = movementEntity.getBalance() + movement.getValueMovement();
        if(move <= 0){
            return CompletableFuture.completedFuture(
                    new AccountResponse<Account>(accountEntity,
                            "Was not possible to make the transaction. "+
                                    "Balance: "+movementEntity.getBalance()+
                                    " value: "+ movement.getValueMovement() + " Not enought money"));
        }
        if(!accountEntity.getAccountType().equals(movement.getMovementType())){
            return CompletableFuture.completedFuture(
                    new AccountResponse<Account>(accountEntity,
                            "Was not possible to make the transaction"+
                                    " Account type and movement type are not compatibles"));
        }
        movement.setBalance(movementEntity.getBalance() + movement.getValueMovement());
        movement.setDateMovement(LocalDateTime.now());
        movement = movementService.save(movement).get();
        return CompletableFuture.completedFuture(
                new AccountResponse<Account>(accountEntity,
                        "Movement complete to account " + accountEntity.getAccountNumber()+
                        ", Init balance: "+movementEntity.getBalance()+", Movement : "+ movement.getValueMovement() +
                                ", Current balance "+movement.getBalance()));
    }
}
