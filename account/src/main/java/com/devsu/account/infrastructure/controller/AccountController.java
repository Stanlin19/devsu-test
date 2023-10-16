package com.devsu.account.infrastructure.controller;

import com.devsu.account.application.facade.IGenerateReport;
import com.devsu.account.application.port.IAcccountService;
import com.devsu.account.domain.dto.Account;
import com.devsu.account.domain.dto.ReportDto;
import com.devsu.account.domain.dto.response.AccountResponse;
import jakarta.persistence.Tuple;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("accounts")
public class AccountController {

    private final IAcccountService accountService;
    private final IGenerateReport generateReport;

    public AccountController(IAcccountService accountService, IGenerateReport generateReport) {
        this.accountService = accountService;
        this.generateReport = generateReport;
    }

    @GetMapping
    public ResponseEntity<List<Account>> findAll() throws InterruptedException, ExecutionException {
        List<Account> clients = accountService.findAll().get();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse<Account>> getAccountById(@PathVariable Long id) throws ExecutionException, InterruptedException {
        AccountResponse<Account> client = accountService.getAccountById(id).get();
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Account> save(@Valid @RequestBody Account account) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(accountService.save(account).get(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@RequestBody Account account, @PathVariable Long id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(accountService.update(account, id).get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws ExecutionException, InterruptedException {
        String res = accountService.delete(id).get();
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/reports/{dates}/{client}")
    public ResponseEntity<List<ReportDto>> generateAccountDetailReport(@PathVariable String dates, @PathVariable Long client)
            throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(generateReport.generateReport(dates, client).get());
    }
}
