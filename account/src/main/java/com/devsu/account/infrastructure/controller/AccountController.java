package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.port.IAccountService;
import com.devsu.account.domain.dto.Account;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Flux<Account>> findAll() throws InterruptedException {
        Flux<Account> clients = accountService.findAll();
        return ResponseEntity.ok(clients.switchIfEmpty(Mono.empty()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Account>> getClientById(@PathVariable Long id){
        Mono<Account> client = accountService.getAccountById(id);
        return ResponseEntity.ok(client.switchIfEmpty(Mono.empty()));
    }

    @PostMapping
    public ResponseEntity<Mono<Account>> save(@Valid @RequestBody Account client){
        return new ResponseEntity<>(accountService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Account>> update(@RequestBody Account client, @PathVariable Long id){
        return ResponseEntity.ok(accountService.update(client, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Object>> delete(@PathVariable Long id){
        Mono<Object> res = accountService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }
}
