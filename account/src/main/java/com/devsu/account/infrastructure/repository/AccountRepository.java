package com.devsu.account.infrastructure.repository;

import com.devsu.account.infrastructure.entity.AccountEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AccountRepository extends R2dbcRepository<AccountEntity, Long> {
    Mono<AccountEntity> findAccountById(Long id);
}
