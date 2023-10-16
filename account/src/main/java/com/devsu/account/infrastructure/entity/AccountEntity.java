package com.devsu.account.infrastructure.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder
public class AccountEntity {

    @Id
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "account_type")
    private String accountType;
    @Column(name = "init_balance")
    private Long initBalance;
    private boolean status;
    @Column(name = "client_id")
    private Long clientId;

    public AccountEntity(){}

    public AccountEntity(Long id, String accountNumber, String accountType, Long initBalance, boolean status, Long clientId) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initBalance = initBalance;
        this.status = status;
        this.clientId = clientId;
    }
}
