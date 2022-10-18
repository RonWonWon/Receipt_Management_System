package com.receiptmanagement.ReceiptManagement.LOGIN;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class LOGINService {
    
    @Autowired
    private LOGINRepository repo;

    public Optional<LOGIN> getById(String id){
        return repo.findById(id);
    }
}
