package com.burkit.shoppingbackend.dao;

import com.burkit.shoppingbackend.dto.Category;

import java.util.List;

public interface CategoryDao
{
    List<Category> list();


    Category get(int id);
}
