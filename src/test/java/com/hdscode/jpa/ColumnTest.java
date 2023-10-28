package com.hdscode.jpa;

import com.hdscode.jpa.entity.Customer;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ColumnTest {

    @Test
    void column(){
        EntityManagerFactory entityManagerFactory = JpaUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName("amevide");
        customer.setPrimaryEmail("amevide@gmail.com");

        entityManager.persist(customer);

        Customer dbCustomer = entityManager.find(Customer.class, customer.getId());
        Assertions.assertNotNull(dbCustomer);

        entityTransaction.commit();
    }
}
