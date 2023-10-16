package com.devsu.account.domain.mapper;

import com.devsu.account.domain.dto.Account;
import com.devsu.account.infrastructure.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountDataMapper {

    public static Account fromAccountEntityToAccount(AccountEntity account){
        return Account.builder()
                .id(account.getId())
                .status(account.isStatus())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initBalance(account.getInitBalance())
                .clientId(account.getClientId())
                .build();
    }

    public static AccountEntity fromAccountToAccountEntity(Account account){
        return AccountEntity.builder()
                .id(account.getId())
                .status(account.isStatus())
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .initBalance(account.getInitBalance())
                .clientId(account.getClientId())
                .build();
    }
}
