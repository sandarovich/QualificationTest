package com.sandarovich.service;

import com.sandarovich.dao.PurchaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    PurchaseDao purchaseDao;

    public long getId() {
        try {
            return purchaseDao.getById(1).getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 9;
    }
}
