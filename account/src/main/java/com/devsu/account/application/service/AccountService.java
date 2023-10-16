package com.devsu.account.application.service;

import com.devsu.account.application.port.IAccountService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.mapper.AccountDataMapper;
import com.devsu.account.infrastructure.entity.AccountEntity;
import com.devsu.account.infrastructure.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService implements IAccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository, AccountDataMapper accountDataMapper) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Flux<Account> findAll() throws InterruptedException {
        logger.info("Searching accounts");
        Flux<AccountEntity> accountsEntity  = accountRepository.findAll();
        logger.info("Searching accounts completed");
        return accountsEntity
                .map(AccountDataMapper::fromAccountEntityToAccount)
                .switchIfEmpty(Flux.empty());
    }

    @Override
    public Mono<Account> getAccountById(Long id) {
        logger.info("Searching account");
        Mono<AccountEntity> accountEntity = accountRepository.findAccountById(id);
        logger.info("Searching account completed");
        return accountEntity.map(AccountDataMapper::fromAccountEntityToAccount)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Account> save(Account account) {
        logger.info("Saving account completed");
        Mono<AccountEntity> accountEntity = accountRepository.save(AccountDataMapper.fromAccountToAccountEntity(account));
        logger.info("Saved account");
        return accountEntity.map(AccountDataMapper::fromAccountEntityToAccount)
                .switchIfEmpty(Mono.empty());
    }

    @Override
    public Mono<Account> update(Account account, Long id) {
        Mono<AccountEntity> accountMono = accountRepository.findById(id);

        return accountMono.flatMap((existingAccount) -> {
            existingAccount.setAccountNumber(account.getAccountNumber());
            existingAccount.setAccountType(account.getAccountType());
            existingAccount.setInitBalance(account.getInitBalance());
            existingAccount.setStatus(account.isStatus());
            existingAccount.setClientId(account.getClientId());
            return accountRepository.save(existingAccount);
        }).map((AccountDataMapper::fromAccountEntityToAccount));
    }

    @Override
    public Mono<Account> updateBalance(Long accountId, Long balance) {
        Mono<AccountEntity> accountMono = accountRepository.findById(accountId);
        return accountMono.flatMap((existingAccount) -> {
            existingAccount.setInitBalance(balance);
            return accountRepository.save(existingAccount);
        }).map((AccountDataMapper::fromAccountEntityToAccount));
    }

    @Override
    public Mono<Object> delete(Long id) {
        Mono<AccountEntity> accountMono = accountRepository.findById(id);
        return accountMono.flatMap(accountRepository::delete);
    }
}
