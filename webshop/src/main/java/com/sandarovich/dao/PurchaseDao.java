package com.sandarovich.dao;


import com.sandarovich.model.Purchase;

public interface PurchaseDao {
    Purchase getById(long id);
}
