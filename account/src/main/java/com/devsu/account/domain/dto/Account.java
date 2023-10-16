package com.devsu.account.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Account {

    private Long id;
    private String accountNumber;
    private String accountType;
    private Long initBalance;
    private boolean status;
    private Long clientId;

    public Account(){}

    public Account(Long id, String accountNumber, String accountType, Long initBalance, boolean status, Long clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initBalance = initBalance;
        this.status = status;
        this.clientId = clientId;
    }
}
