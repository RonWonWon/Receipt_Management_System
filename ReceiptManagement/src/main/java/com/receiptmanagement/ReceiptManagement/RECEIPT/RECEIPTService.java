package com.receiptmanagement.ReceiptManagement.RECEIPT;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RECEIPTService {
    
    @Autowired
    private RECEIPTRepository repo;

    public List<RECEIPT> listAll(){
        return repo.findAll();
    }
    
    public RECEIPT getById(long id){
        return repo.findById(id);
    }

    public List<RECEIPT> getByName(String name){
        return repo.findByName(name);
    }

    public List<RECEIPT> getByMobile(String mob){
        return repo.findByMobile(mob);
    }

    public void add(RECEIPT receipt){
        repo.save(receipt);
    }
}
