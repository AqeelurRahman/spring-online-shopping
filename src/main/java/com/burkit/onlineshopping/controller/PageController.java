package com.burkit.onlineshopping.controller;

import com.burkit.onlineshopping.exception.ProductNotFoundException;
import com.burkit.shoppingbackend.dao.CategoryDao;
import com.burkit.shoppingbackend.dao.ProductDao;
import com.burkit.shoppingbackend.dto.Category;
import com.burkit.shoppingbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Authenticator;

@Controller
public class  PageController {

    private static Logger logger = LoggerFactory.getLogger(PageController.class);


    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = {"/", "/home", "/index"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("page");

        logger.info("Inside PageController Index method -INFO");
        logger.info("Inside PageController Index method -DEBUG");
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
    @RequestMapping(value = {"/login"})
    public ModelAndView login(@RequestParam (name = "error", required = false) String error
    ,                         @RequestParam (name = "logout", required = false) String logout){
        ModelAndView mv = new ModelAndView("login");
        if (error!=null){
            mv.addObject("message","Invalid Username And Password");
        }
        if (logout!=null){
            mv.addObject("logout","User has successfully logged out!");
        }
        mv.addObject("title", "Login");
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
    public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
        ModelAndView mv = new ModelAndView("page");
        Product product = productDao.get(id);
        if (product == null) throw new ProductNotFoundException();

        //upate the views count
        product.setViews(product.getViews() + 1);
        productDao.update(product);

        mv.addObject("title", product.getName());
        mv.addObject("product", product);

        mv.addObject("userClickShowProduct", true);
        return mv;
    }

//    access denied page
@RequestMapping(value = {"/access-denied"})
public ModelAndView accessDenied() {
    ModelAndView mv = new ModelAndView("error");
    mv.addObject("title", "403 Access Denied");
    mv.addObject("errorTitle", "403 Access Denied");
    mv.addObject("errorDescription", "Gotcha! You are not allowed Here");
    return mv;
}

//Logout
    @RequestMapping(value = "/perform-logout")

    public String logout(HttpServletRequest request, HttpServletResponse response){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);

        }

        return "redirect:/login?logout";
    }
}
