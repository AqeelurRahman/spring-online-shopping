package com.burkit.onlineshopping.controller;

import javax.servlet.http.HttpSession;

import com.burkit.onlineshopping.model.UserModel;
import com.burkit.shoppingbackend.dao.UserDao;
import com.burkit.shoppingbackend.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//this controller works above all controllers. @ControllerAdvice annotaion is used
@ControllerAdvice
public class GlobalController {
	
	
	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private HttpSession session;
	
	private UserModel userModel = null;
	private User user = null;
	
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if(session.getAttribute("userModel")==null) {
			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			if(!authentication.getPrincipal().equals("anonymousUser")){
				// get the user from the database
				user = userDAO.getByEmail(authentication.getName());
				
				if(user!=null) {
					// create a new model
					userModel = new UserModel();
					// set the name and the id
					userModel.setId(user.getId());
					userModel.setFullName(user.getFirstName() + " " + user.getLastName());
					userModel.setRole(user.getRole());
					
					if(user.getRole().equals("USER")) {
						userModel.setCart(user.getCart());					
					}				
	
					session.setAttribute("userModel", userModel);
					return userModel;
				}			
			}
		}
		
		return (UserModel) session.getAttribute("userModel");		
	}
		
}
