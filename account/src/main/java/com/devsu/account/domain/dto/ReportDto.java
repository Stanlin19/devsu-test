package com.devsu.account.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class ReportDto {

    private String client;
    private Timestamp date;
    private String account_number;
    private String type;
    private Long init_balance;
    private boolean status;
    private Long movement;
    private Long balance;

    public ReportDto() {
    }

    public ReportDto(Timestamp date, String client,  String account_number, String type, Long init_balance, boolean status, Long movement, Long balance) {
        this.date = date;
        this.client = client;
        this.account_number = account_number;
        this.type = type;
        this.init_balance = init_balance;
        this.status = status;
        this.movement = movement;
        this.balance = balance;
    }
}
