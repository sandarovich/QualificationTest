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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:*applicationContextTest.xml"})

public class PurchaseMappingTest {

    @PersistenceContext
    private EntityManager em;

    private Date date1;
    private Date date2;


    @Before
    public void init() {

        setDates();

        Purchase purchase1 = new Purchase();
        purchase1.setPurchaseDate(date1);

        Purchase purchase2 = new Purchase();
        purchase2.setPurchaseDate(date2);

        em.merge(purchase1);
        em.merge(purchase2);
    }

    @After
    public void tearDown() {
        Query q1 = em.createQuery("Delete from Purchase");
        q1.executeUpdate();
    }

    @Test
    public void testPurchaseMapping() {
        TypedQuery<Purchase> query = em.createNamedQuery("Purchase.getByDate", Purchase.class);
        query.setParameter("date", date1);
        List<Purchase> purchases = query.getResultList();
        assertThat(purchases.get(0).getPurchaseDate(), is(date1));

        query = em.createNamedQuery("Purchase.getByDate", Purchase.class);
        query.setParameter("date", date2);
        purchases = query.getResultList();
        assertThat(purchases.get(0).getPurchaseDate(), is(date2));
    }

    private void setDates() {
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.YEAR, 2015);
        cal1.set(Calendar.MONTH, 5);
        cal1.set(Calendar.DAY_OF_MONTH, 4);
        date1 = cal1.getTime();

        Calendar cal2 = Calendar.getInstance();
        cal2.set(Calendar.YEAR, 2016);
        cal2.set(Calendar.MONTH, 3);
        cal2.set(Calendar.DAY_OF_MONTH, 1);
        date2 = cal2.getTime();
    }
}
