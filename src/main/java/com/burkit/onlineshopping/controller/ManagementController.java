package com.burkit.onlineshopping.controller;

import com.burkit.onlineshopping.util.FileUploadUtility;
import com.burkit.onlineshopping.validator.ProductValidator;
import com.burkit.shoppingbackend.dao.CategoryDao;
import com.burkit.shoppingbackend.dao.ProductDao;
import com.burkit.shoppingbackend.dto.Category;
import com.burkit.shoppingbackend.dto.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/manage")
public class ManagementController {

    private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

    @Autowired
    private ProductDao productDAO;

    @Autowired
    private CategoryDao categoryDAO;

    @RequestMapping("/products")
    public ModelAndView manageProduct(@RequestParam(name = "success", required = false) String success) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Product Management");
        mv.addObject("userClickManageProducts", true);

        Product nProduct = new Product();

        // assuming that the user is ADMIN
        // later we will fixed it based on user is SUPPLIER or ADMIN
        nProduct.setSupplierId(1);
        nProduct.setActive(true);

        mv.addObject("product", nProduct);


        if (success != null) {
            if (success.equals("product")) {
                mv.addObject("message", "Product submitted successfully!");
            } else if (success.equals("category")) {
                mv.addObject("message", "Category submitted successfully!");
            }
        }

        return mv;

    }


    @RequestMapping("/{id}/product")
    public ModelAndView manageProductEdit(@PathVariable int id) {

        ModelAndView mv = new ModelAndView("page");
        mv.addObject("title", "Product Management");
        mv.addObject("userClickManageProducts", true);

        // Product nProduct = new Product();
        mv.addObject("product", productDAO.get(id));


        return mv;

    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct,
                                    BindingResult results, Model model, HttpServletRequest request) {

//         mandatory file upload check
        if (mProduct.getId() == 0) {
            new ProductValidator().validate(mProduct, results);
        } else {
            // edit check only when the file has been selected
            if (!mProduct.getFile().getOriginalFilename().equals("")) {
                new ProductValidator().validate(mProduct, results);
            }
        }
        if (results.hasErrors()) {
            model.addAttribute("message", "Validation fails for adding the product!");
            model.addAttribute("userClickManageProducts", true);


            return "page";
        }


        if (mProduct.getId() == 0) {
            productDAO.add(mProduct);


        } else {
            productDAO.update(mProduct);
        }
        //upload the file
        if (!mProduct.getFile().getOriginalFilename().equals("")) {
                FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
            }


        return "redirect:/manage/products?success=product";
    }


    @RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
    @ResponseBody
    public String managePostProductActivation(@PathVariable int id) {
//       Fetching product from database
        Product product = productDAO.get(id);

        boolean isActive = product.isActive();
        product.setActive(!isActive);
        productDAO.update(product);
        return (isActive) ? "Product Dectivated Successfully!" : "Product Activated Successfully";
    }

//to handle category modal-form submission

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public String managePostCategory(@ModelAttribute("category") Category mCategory, HttpServletRequest request) {
        categoryDAO.add(mCategory);
        return "redirect:" + request.getHeader("Referer") + "?success=category";
    }


    @ModelAttribute("categories")
    public List<Category> modelCategories() {
        return categoryDAO.list();
    }

    @ModelAttribute("category")
    public Category modelCategory() {
        return new Category();
    }


}


