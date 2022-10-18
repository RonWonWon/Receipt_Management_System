package com.receiptmanagement.ReceiptManagement.LOGIN;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LOGINRepository extends JpaRepository<LOGIN, String>{
    Optional<LOGIN> findById(String id);
}
