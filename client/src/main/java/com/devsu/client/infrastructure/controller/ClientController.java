package com.devsu.client.infrastructure.controller;

import com.devsu.client.application.port.IClientService;
import com.devsu.client.domain.dto.Client;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("clients")
public class ClientController {

    private final IClientService iClientService;

    public ClientController(IClientService iClientService) {
        this.iClientService = iClientService;
    }

    @GetMapping
    public ResponseEntity<Flux<Client>> findAll() throws InterruptedException {
        Flux<Client> clients = iClientService.findAll();
        return ResponseEntity.ok(clients.switchIfEmpty(Mono.empty()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Client>> getClientById(@PathVariable Long id){
        Mono<Client> client = iClientService.getClientById(id);
        return ResponseEntity.ok(client.switchIfEmpty(Mono.empty()));
    }

    @PostMapping
    public ResponseEntity<Mono<Client>> save(@Valid @RequestBody Client client){
        return new ResponseEntity<>(iClientService.save(client), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mono<Client>> update(@RequestBody Client client, @PathVariable Long id){
        return ResponseEntity.ok(iClientService.update(client, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mono<Object>> delete(@PathVariable Long id){
        Mono<Object> res = iClientService.delete(id);
        return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }
}
