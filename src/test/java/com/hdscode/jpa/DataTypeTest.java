package com.hdscode.jpa;

import com.hdscode.jpa.entity.Customer;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataTypeTest {

    private EntityManagerFactory entityManagerFactory;


    @BeforeEach
    void setUp(){
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory();
    }

    @Test
    void dataType(){
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
        customer.setPrimaryEmail("amevide@gmail.com");
        customer.setAge((byte) 20);
        customer.setMarried(true);
        entityManager.persist(customer);

        // cek in db

        Customer dbCustomer = entityManager.find(Customer.class, "8935b0eb-b88a-475d-b922-193ac8be4072");

        Assertions.assertEquals(customer.getId(), dbCustomer.getId());
        Assertions.assertEquals(customer.getName(), dbCustomer.getName());
        Assertions.assertEquals(customer.getPrimaryEmail(), dbCustomer.getPrimaryEmail());
        Assertions.assertEquals(customer.getAge(),dbCustomer.getAge());
        Assertions.assertEquals(customer.isMarried() ,dbCustomer.isMarried());

        entityTransaction.commit();
        entityManager.close();
    }
}
