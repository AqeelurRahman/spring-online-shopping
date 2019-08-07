package com.burkit.onlineshopping.controller;

import com.burkit.shoppingbackend.dao.CategoryDao;
import com.burkit.shoppingbackend.dao.ProductDao;
import com.burkit.shoppingbackend.dto.Category;
import com.burkit.shoppingbackend.dto.Product;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Home");
        mv.addObject("categories", categoryDao.list());
        mv.addObject("userClickHome", true);
        return mv;
    }

    @RequestMapping(value = {"/about"})
    public ModelAndView about() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "About Us");
        mv.addObject("userClickAbout", true);
        return mv;
    }

    @RequestMapping(value = {"/contact"})
    public ModelAndView contact() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Contact Us");
        mv.addObject("userClickContact", true);
        return mv;
    }

    //    Methods to load all the products
    @RequestMapping(value = {"/show/all/products"})
    public ModelAndView showAllProducts() {
        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "All Products");
        mv.addObject("categories", categoryDao.list());
        mv.addObject("userClickAllProducts", true);
        return mv;
    }

    //
    @RequestMapping(value = {"/show/category/{id}/products"})
    public ModelAndView showCategoryProduct(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("page");
//        Category DAO to fetch a single category
        Category category = null;
        category = categoryDao.get(id);
        mv.addObject("title", category.getName());
        mv.addObject("categories", categoryDao.list());
        //passing single category object
        mv.addObject("category", category);

        mv.addObject("userClickCategoryProduct", true);
        return mv;
    }

//    viewing a single page product

    @RequestMapping(value = "/show/{id}/product")
    public ModelAndView showSingleProduct(@PathVariable int id){
        ModelAndView mv = new ModelAndView("page");
        Product product = productDao.get(id);
        //upate the views count
        product.setViews(product.getViews() +1);
        productDao.update(product);

        mv.addObject("title",product.getName());
        mv.addObject("product", product);

        mv.addObject("userClickShowProduct",true);
        return mv;
    }


}
