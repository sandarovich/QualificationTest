package com.sandarovich.dao;


import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;

public interface PurchaseDao {
    Purchase getById(long id);

    Purchase save();

    PurchaseItem saveItem(PurchaseItem purchaseItem);
}
