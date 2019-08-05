package com.burkit.shoppingbackend.dao;

import com.burkit.shoppingbackend.dto.Product;

import java.util.List;

public interface ProductDao
{
    Product get(int productId);
    List<Product> list ();
    boolean add(Product product);
    boolean update(Product product);
    boolean delete(Product product);

//    Buisness Method

    List<Product> listActiveProducts();
    List<Product> listActiveProductsByCategory(int categoryId);
    List<Product> getLatestActiveProducts(int count);


    List<Product> getProductsByParam(String param, int count);
}
