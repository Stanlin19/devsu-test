package com.devsu.account.application.port;

import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.ReportDto;
import com.devsu.account.domain.dto.response.AccountResponse;
import com.devsu.account.infrastructure.entity.MovementEntity;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public interface IAcccountService {

    CompletableFuture<List<Account>> findAll() throws InterruptedException;
    CompletableFuture<AccountResponse<Account>> getAccountById(Long id);
    CompletableFuture<Account> save(Account account);
    CompletableFuture<Account> update(Account account, Long id);
    CompletableFuture<String> delete(Long id);

    CompletableFuture<List<Tuple>> getReportDataFromAccount(String dates, Long clientId);
}
