package com.devsu.account.application.facade;

import com.devsu.account.application.port.IAccountService;
import com.devsu.account.application.port.IMovementService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.Movement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RegisterMovements {

    private static final Logger logger = LoggerFactory.getLogger(RegisterMovements.class);

    private final IMovementService movementService;
    private final IAccountService accountService;

    public RegisterMovements(IMovementService movementService, IAccountService accountService) {
        this.movementService = movementService;
        this.accountService = accountService;
    }

    public Mono<Movement> registerMovement(Movement movement, Long accountId){
        return accountService.getAccountById(accountId).flatMap(a->{
           a.setInitBalance(movement.getBalance() + movement.getValueMovement());
           return accountService.update(a, accountId);
        }).flatMap(x -> {
            movement.setBalance(movement.getBalance() + movement.getValueMovement());
            return movementService.save(movement);
        });
    }
}
