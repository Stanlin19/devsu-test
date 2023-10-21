package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.facade.RegisterMovements;
import com.devsu.account.application.port.IMovementService;
import com.devsu.account.domain.dto.Movement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("movements")
public class MovementController {

    private final IMovementService movementService;
    private final RegisterMovements registerMovements;

    public MovementController(IMovementService movementService, RegisterMovements registerMovements) {
        this.movementService = movementService;
        this.registerMovements = registerMovements;
    }

    @GetMapping
    public ResponseEntity<Flux<Movement>> findAll() throws InterruptedException {
        Flux<Movement> clients = movementService.findAll();
        return ResponseEntity.ok(clients.switchIfEmpty(Mono.empty()));
    }

    @PostMapping("/account/{accountId}")
    public ResponseEntity<Mono<Movement>> save(@Valid @RequestBody Movement movement,
                                               @PathVariable("accountId") Long accountId){

        return ResponseEntity.ok(registerMovements.registerMovement(movement, accountId));
        //return new ResponseEntity<>(movementService.save(movement), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Movement>> update(@RequestBody Movement movement, @PathVariable Long id){
        return ResponseEntity.ok(movementService.update(movement, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Object>> delete(@PathVariable Long id){
        Mono<Object> res = movementService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }
}
