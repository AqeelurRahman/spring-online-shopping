package com.burkit.shoppingbackend.daoimpl;

import com.burkit.shoppingbackend.dao.CategoryDao;
import com.burkit.shoppingbackend.dto.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao {

    private  static List<Category> categories = new ArrayList<>();

    static {
        Category category = new Category();
        category.setId(1);
        category.setDescription("CategoryDescripption etc");
        category.setName("Laptops");
        category.setImageURL("CAT_1.png");
    categories.add(category);

         category = new Category();
        category.setId(2);
        category.setDescription("Category2Descripption etc");
        category.setName("Computers");
        category.setImageURL("CAT_2.png");
        categories.add(category);

        category = new Category();
        category.setId(3);
        category.setDescription("Category3Descripption etc");
        category.setName("Comasdfputers");
        category.setImageURL("CAT_2.png");
        categories.add(category);


    }




    @Override
    public List<Category> list() {
        return categories;
    }

    @Override
    public Category get(int id) {
        //enhanced for loop
        for (Category category : categories){
            if (category.getId()==id){
                return category;
            }
        }
        return null;
    }
}
