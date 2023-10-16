package com.devsu.account.infrastructure.repository;

import com.devsu.account.infrastructure.entity.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovementRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findMovementByAccountIdOrderByIdDesc(Long id);
}
