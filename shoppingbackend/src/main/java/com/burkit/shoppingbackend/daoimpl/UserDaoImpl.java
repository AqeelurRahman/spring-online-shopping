package com.burkit.shoppingbackend.daoimpl;

import com.burkit.shoppingbackend.dao.ProductDao;
import com.burkit.shoppingbackend.dao.UserDao;
import com.burkit.shoppingbackend.dto.Address;
import com.burkit.shoppingbackend.dto.Product;
import com.burkit.shoppingbackend.dto.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getByEmail(String email) {
        String selectQuery = "FROM User WHERE email = :email";//:email is the parameter which pass through setParameter method
        try {
            return sessionFactory
                    .getCurrentSession()
                    .createQuery(selectQuery,User.class)
                    .setParameter("email",email)
                    .getSingleResult();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean add(User user) {
        try {
            sessionFactory.getCurrentSession().persist(user);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean addAddress(Address address) {
        try {
            // will look for this code later and why we need to change it
            sessionFactory.getCurrentSession().persist(address);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }

    @Override
    public boolean updateAddress(Address address) {
        try {
            sessionFactory.getCurrentSession().update(address);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }


    @Override
    public List<Address> listShippingAddresses(int userId) {
        String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
        return sessionFactory
                .getCurrentSession()
                .createQuery(selectQuery,Address.class)
                .setParameter("userId", userId)
                .setParameter("isShipping", true)
                .getResultList();

    }

    @Override
    public Address getBillingAddress(int userId) {
        String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
        try{
            return sessionFactory
                    .getCurrentSession()
                    .createQuery(selectQuery,Address.class)
                    .setParameter("userId", userId)
                    .setParameter("isBilling", true)
                    .getSingleResult();
        }
        catch(Exception ex) {
            return null;
        }
    }

    @Override
    public User get(int id) {
        try {
            return sessionFactory.getCurrentSession().get(User.class, id);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Address getAddress(int addressId) {
        try {
            return sessionFactory.getCurrentSession().get(Address.class, addressId);
        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
