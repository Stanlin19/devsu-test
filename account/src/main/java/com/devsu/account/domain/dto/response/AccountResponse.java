package com.devsu.account.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AccountResponse<T> {

    private T object;
    private String message;

    public AccountResponse(T object){
        this.object = object;
    }
}
