package com.sandarovich.dao.impl;


import com.sandarovich.dao.ProductDao;
import com.sandarovich.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class ProductDaoPostgreImpl implements ProductDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Product getByName(String name) {
        TypedQuery<Product> query = em.createNamedQuery("Product.getByName", Product.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<Product> getAll() {
        TypedQuery<Product> query = em.createNamedQuery("", Product.class);
        return query.getResultList();
    }

    @Override
    public Product save(Product product) {
        return em.merge(product);

    }

    @Override
    public boolean isExist(String name) {
        TypedQuery<Long> query = em.createNamedQuery("Product.isExist", Long.class);
        query.setParameter("name", name);
        return query.getSingleResult() == 1;
    }
}
