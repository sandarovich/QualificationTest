package com.sandarovich.model;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@Transactional(propagation = Propagation.REQUIRES_NEW)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*applicationContextTest.xml"})

public class ProductMappingTest {
    @PersistenceContext
    private EntityManager em;

    @Before
    public void init() {
        Product product1 = new Product();
        product1.setName("product1");
        product1.setPrice(12.1);

        Product product2 = new Product();
        product2.setName("product2");
        product2.setPrice(11.1);

        em.merge(product1);
        em.merge(product2);
    }

    @After
    public void tearDown() {
        Query q = em.createQuery("Delete from Product");
        q.executeUpdate();
    }

    @Test
    public void testProductMapping() {
        TypedQuery<Product> query = em.createNamedQuery("Product.getAll", Product.class);
        List<Product> products = query.getResultList();
        Product product1 = products.get(0);
        assertThat(product1.getName(), is("product1"));
        assertThat(product1.getPrice(), is(12.1));

        Product product2 = products.get(1);
        assertThat(product2.getName(), is("product2"));
        assertThat(product2.getPrice(), is(11.1));

    }


}

