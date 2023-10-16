package com.devsu.account.application.facade;

import com.devsu.account.application.port.IAccountService;
import com.devsu.account.application.port.IMovementService;
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

    public Mono<String> registerMovement(Movement movement, Long accountId){
        movement.setBalance(movement.getBalance() - movement.getValueMovement());
        movementService.save(movement);
        accountService.updateBalance(accountId, movement.getBalance());
        return Mono.just("Created movement");
    }
}
