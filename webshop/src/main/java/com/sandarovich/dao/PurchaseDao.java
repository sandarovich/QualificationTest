package com.sandarovich.dao;


import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;

import java.util.Date;
import java.util.List;

public interface PurchaseDao {
    List<Purchase> getByDate(Date date);
    Purchase save();
    PurchaseItem saveItem(PurchaseItem purchaseItem);

}
