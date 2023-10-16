package com.devsu.account.infrastructure.repository;

import com.devsu.account.infrastructure.entity.MovementEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface MovementReporitory extends R2dbcRepository<MovementEntity, Long> {
}
