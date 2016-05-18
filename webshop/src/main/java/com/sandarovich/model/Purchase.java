package com.sandarovich.model;

import javax.persistence.*;
import java.security.Timestamp;
import java.util.Set;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "purchase_date")
    private Timestamp purchaseDate;

    @OneToMany(mappedBy = "purchase")
    private Set<PurchaseItem> purchaseItemSet;
}
