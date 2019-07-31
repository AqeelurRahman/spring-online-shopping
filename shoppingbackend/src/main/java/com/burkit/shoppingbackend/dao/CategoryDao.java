package com.burkit.shoppingbackend.dao;

import com.burkit.shoppingbackend.dto.Category;

import java.util.List;

public interface CategoryDao
{
    Category get(int id);
    List<Category> list();
    boolean add(Category category);
    boolean update(Category category);
    boolean delete(Category category);

}
