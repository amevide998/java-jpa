package com.hdscode.jpa;


import com.hdscode.jpa.entity.Customer;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class CRUDTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp(){
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory();
    }

    @Test
    void create(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete
        Customer deleteCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        if(deleteCustomer != null){
            entityManager.remove(deleteCustomer);
        }

        Customer customer = new Customer();
        customer.setId("8935b0eb-b88a-475d-b922-193ac8be4072");
        customer.setName("amevide");
        entityManager.persist(customer);


        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void find(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete
        Customer deleteCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        if(deleteCustomer != null){
            entityManager.remove(deleteCustomer);
        }

        // create user
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("8935b0eb-b88a-475d-b922-193ac8be4072");
        dummyCustomer.setName("amevide");
        entityManager.persist(dummyCustomer);

        // find
        Customer customer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("amevide", customer.getName());

        entityTransaction.commit();
        entityManager.close();
    }

    @Test
    void update(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete
        Customer deleteCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        if(deleteCustomer != null){
            entityManager.remove(deleteCustomer);
        }

        // create user
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("8935b0eb-b88a-475d-b922-193ac8be4072");
        dummyCustomer.setName("amevide");
        entityManager.persist(dummyCustomer);

        Customer customer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        customer.setName("amevide update");
        entityManager.merge(customer);

        // get customer from db
        Customer dbCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        Assertions.assertNotNull(dbCustomer);
        Assertions.assertEquals("amevide update", dbCustomer.getName());

        entityTransaction.commit();
        entityManager.close();
    }


    @Test
    void delete(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete
        Customer deleteCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        if(deleteCustomer != null){
            entityManager.remove(deleteCustomer);
        }


        // create user
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("8935b0eb-b88a-475d-b922-193ac8be4072");
        dummyCustomer.setName("amevide");
        entityManager.persist(dummyCustomer);

        Customer customer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        entityManager.remove(customer);

        // get customer from db
        Customer dbCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");
        Assertions.assertNull(dbCustomer);

        entityTransaction.commit();
        entityManager.close();
    }

}
