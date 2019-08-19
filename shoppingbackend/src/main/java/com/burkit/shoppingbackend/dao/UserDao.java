package com.burkit.shoppingbackend.dao;

import com.burkit.shoppingbackend.dto.Address;
import com.burkit.shoppingbackend.dto.Category;
import com.burkit.shoppingbackend.dto.User;

import java.util.List;

public interface UserDao
{

    User getByEmail(String email);
    User get(int id);

    boolean add(User user);

    // adding and updating a new address
    Address getAddress(int addressId);
    boolean addAddress(Address address);
    boolean updateAddress(Address address);
    Address getBillingAddress(int userId);
    List<Address> listShippingAddresses(int userId);
}
