package com.receiptmanagement.ReceiptManagement.RECEIPT;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RECEIPTRepository extends JpaRepository<RECEIPT, Long>{
    RECEIPT save(RECEIPT receipt);
    RECEIPT findById(long id);
    void deleteById(long id);
    List<RECEIPT> findByName(String name);
    List<RECEIPT> findByMobile(String mobile);
}
