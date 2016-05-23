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
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*applicationContextTest.xml"})
public class PurchaseItemMappingTest {

    @PersistenceContext
    private EntityManager em;


    @Before
    public void init() {
        Product product = new Product();
        product.setName("ZZ1");
        product.setPrice(12.21);
        em.persist(product);

        Purchase purchase = new Purchase();
        purchase.setPurchaseDate(setDate());
        em.merge(purchase);

        PurchaseItem purchaseItem = new PurchaseItem();
        purchaseItem.setProduct(product);
        purchaseItem.setQuantity(343);
        purchaseItem.setPurchase(purchase);

        em.merge(purchaseItem);
    }

    @After
    public void tearDown() {
        Query q1 = em.createQuery("Delete from Purchase");
        Query q2 = em.createQuery("Delete from Product");
        Query q3 = em.createQuery("Delete from PurchaseItem");

        q3.executeUpdate();
        q2.executeUpdate();
        q1.executeUpdate();
    }

    @Test
    public void testPurchaseItemMapping() {
        TypedQuery<PurchaseItem> query = em.createNamedQuery("PurchaseItem.getPurchaseItem", PurchaseItem.class);
        query.setParameter("id", 1l);
        PurchaseItem purchaseItem = query.getSingleResult();
        assertThat(purchaseItem.getQuantity(), is(343l));
        assertThat(purchaseItem.getProduct().getName(), is("ZZ1"));
        assertThat(purchaseItem.getProduct().getPrice(), is(12.21));
        assertThat(purchaseItem.getPurchase().getPurchaseDate().toString(), is(setDate().toString()));
    }

    private Date setDate() {
        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, 3);
        cal2.set(Calendar.DAY_OF_MONTH, 1);
        return cal2.getTime();
    }

}
