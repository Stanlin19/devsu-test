package com.devsu.account.application.facade;

import com.devsu.account.domain.dto.ReportDto;
import com.devsu.account.domain.dto.response.AccountResponse;
import jakarta.persistence.Tuple;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Async
public interface IGenerateReport {

    CompletableFuture<List<ReportDto>> generateReport(String dates, Long clientId) throws ExecutionException, InterruptedException;
}
