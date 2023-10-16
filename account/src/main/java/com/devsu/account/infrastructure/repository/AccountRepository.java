package com.devsu.account.infrastructure.repository;

import com.devsu.account.domain.dto.ReportDto;
import com.devsu.account.infrastructure.entity.AccountEntity;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findAccountById(Long id);

    @Query(value = "SELECT m.date_movement as date, c.name as client, a.account_number, a.account_type as type, " +
            "a.init_balance, a.status, m.value_movement as movement, m.balance " +
            "from account a " +
            "INNER JOIN client c ON c.id = a.client_id " +
            "INNER JOIN movement m ON m.account_id = a.id " +
            "WHERE c.id = (?3) " +
            "AND m.date_movement BETWEEN (?1) and (?2)", nativeQuery = true)
    List<Tuple> findDataByAccountId(String init, String end, Long clientId);
}
