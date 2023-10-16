package com.devsu.account.application.facade;

import com.devsu.account.application.port.IAcccountService;
import com.devsu.account.domain.dto.ReportDto;
import jakarta.persistence.Tuple;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
@Async
public class GenerateReport implements IGenerateReport{

    private final IAcccountService acccountService;

    public GenerateReport(IAcccountService acccountService) {
        this.acccountService = acccountService;
    }

    @Override
    public CompletableFuture<List<ReportDto>> generateReport(String dates, Long clientId) throws ExecutionException, InterruptedException {
        List<Tuple> tuple = acccountService.getReportDataFromAccount(dates, clientId).get();
        List<ReportDto> response = tuple.stream().map(t -> new ReportDto(
                        t.get(0, Timestamp.class),
                        t.get(1, String.class),
                        t.get(2, String.class),
                        t.get(3, String.class),
                        t.get(4, Long.class),
                        t.get(5, Boolean.class),
                        t.get(6, Long.class),
                        t.get(7, Long.class)
                )).toList();
        return CompletableFuture.completedFuture(response);
    }
}
