package com.sandarovich.dao.impl;

import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.model.Purchase;
import com.sandarovich.model.PurchaseItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;

@Repository
@Transactional
public class PurchaseDaoPostgreImpl implements PurchaseDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Purchase getById(long id) {
        TypedQuery<Purchase> query = em.createNamedQuery("Purchase.getById", Purchase.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Purchase save() {
        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(new Date());
        return em.merge(purchase);
    }

    @Override
    public PurchaseItem saveItem(PurchaseItem purchaseItem) {
        return em.merge(purchaseItem);
    }

}
