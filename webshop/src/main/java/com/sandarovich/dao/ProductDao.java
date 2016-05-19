package com.sandarovich.dao;

import com.sandarovich.model.Product;

import java.util.List;

public interface ProductDao {
    Product getByName(String name);

    List<Product> getAll();

    void save(Product product);

}
