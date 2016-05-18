package com.sandarovich.dao.impl;


import com.sandarovich.dao.PurchaseDao;
import com.sandarovich.model.Purchase;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class PurchaseDaoPostgreImpl implements PurchaseDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Purchase getById(long id) {
        TypedQuery<Purchase> query = em.createNamedQuery("Purchase.getById", Purchase.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


}
