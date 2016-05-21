package com.sandarovich.model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "Product.isExist", query = "SELECT COUNT(p) from Product as p WHERE p.name = :name"),
        @NamedQuery(name = "Product.getByName", query = "SELECT p from Product as p WHERE p.name = :name"),
        @NamedQuery(name = "Product.getAll", query = "SELECT p from Product as p")

})
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
