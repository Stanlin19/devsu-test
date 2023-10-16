package com.devsu.account.application.facade;

import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.Movement;
import com.devsu.account.domain.dto.response.AccountResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Async
public interface IRegisterMovement {

    CompletableFuture<AccountResponse<Account>> registerMovement(Movement movement) throws ExecutionException, InterruptedException;
}
