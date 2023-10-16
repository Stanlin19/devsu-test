package com.devsu.account.application.port;

import com.devsu.account.domain.dto.Account;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface IAccountService {

    Flux<Account> findAll() throws InterruptedException;
    Mono<Account> getAccountById(Long id);
    Mono<Account> save(Account account);
    Mono<Account> update(Account account, Long id);
    Mono<Account> updateBalance(Long accountId, Long balance);
    Mono<Object> delete(Long id);
}
