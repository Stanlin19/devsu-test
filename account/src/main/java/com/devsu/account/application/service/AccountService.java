package com.devsu.account.application.service;

import com.devsu.account.application.port.IAcccountService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.ReportDto;
import com.devsu.account.domain.dto.response.AccountResponse;
import com.devsu.account.domain.mapper.AccountDataMapper;
import com.devsu.account.infrastructure.entity.AccountEntity;
import com.devsu.account.infrastructure.repository.AccountRepository;
import jakarta.persistence.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Async
public class AccountService implements IAcccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public CompletableFuture<List<Account>> findAll() throws InterruptedException {
        logger.info("Searching accounts");
        List<Account> accounts = accountRepository.findAll()
                .stream().map(AccountDataMapper::fromAccountEntityToAccount)
                .toList();
        logger.info("Searching accounts completed");
        return CompletableFuture.completedFuture(accounts);
    }

    @Override
    public CompletableFuture<AccountResponse<Account>> getAccountById(Long id) {
        logger.info("Searching account");
        Optional<AccountEntity> accountEntity = accountRepository.findById(id);
        logger.info("Searching account completed");
        if(!accountEntity.isPresent()){
            return CompletableFuture.completedFuture(new AccountResponse<Account>(AccountDataMapper.fromAccountEntityToAccount(accountEntity.get()),
                    "Account with id "+id+" was not found"));
        }
        return CompletableFuture.completedFuture(new AccountResponse<Account>(
                AccountDataMapper.fromAccountEntityToAccount(accountEntity.get())));
    }

    @Override
    public CompletableFuture<Account> save(Account account) {
        logger.info("Saving account completed");
        AccountEntity accountEntity = accountRepository.save(AccountDataMapper.fromAccountToAccountEntity(account));
        logger.info("Saved account");
        return CompletableFuture.completedFuture(AccountDataMapper.fromAccountEntityToAccount(accountEntity));
    }

    @Override
    public CompletableFuture<Account> update(Account account, Long id) {
        logger.info("Updating account completed");
        AccountEntity accountEntity = accountRepository.save(AccountDataMapper.fromAccountToAccountEntity(account));
        logger.info("Updated account");
        return CompletableFuture.completedFuture(AccountDataMapper.fromAccountEntityToAccount(accountEntity));
    }

    @Override
    public CompletableFuture<String> delete(Long id) {
        Optional<AccountEntity> account = accountRepository.findById(id);
        if(account.isEmpty()){
            return CompletableFuture.completedFuture("Client with id "+id+" was not found");
        } else {
            accountRepository.deleteById(id);
            return CompletableFuture.completedFuture("Client deleted");
        }
    }

    @Override
    public CompletableFuture<List<Tuple>> getReportDataFromAccount(String dates, Long clientId) {
        String[] dateRange = dates.split(" ");
        return CompletableFuture.completedFuture(accountRepository.findDataByAccountId(dateRange[0], dateRange[1], clientId));
    }
}
