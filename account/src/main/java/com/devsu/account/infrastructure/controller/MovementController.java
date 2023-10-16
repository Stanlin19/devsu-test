package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.facade.IRegisterMovement;
import com.devsu.account.application.port.IMovementService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.Movement;
import com.devsu.account.domain.dto.response.AccountResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("movements")
public class MovementController {

    private final IMovementService movementService;
    private final IRegisterMovement registerMovement;

    public MovementController(IMovementService movementService, IRegisterMovement registerMovement) {
        this.movementService = movementService;
        this.registerMovement = registerMovement;
    }

    @GetMapping
    public ResponseEntity<List<Movement>> findAll() throws InterruptedException, ExecutionException {
        List<Movement> movements = movementService.findAll().get();
        return ResponseEntity.ok(movements);
    }

    @PostMapping
    public ResponseEntity<AccountResponse<Account>> save(@Valid @RequestBody Movement movement) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(registerMovement.registerMovement(movement).get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movement> update(@RequestBody Movement movement, @PathVariable Long id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(movementService.update(movement, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ExecutionException, InterruptedException {
        String res = movementService.delete(id).get();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
