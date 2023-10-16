package com.devsu.client.infrastructure.repository;

import com.devsu.client.infrastructure.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    ClientEntity findClientById(Long id);
}
