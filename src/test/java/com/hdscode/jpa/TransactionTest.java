package com.hdscode.jpa;

import com.hdscode.jpa.entity.Customer;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransactionTest {

    @Test
    void transaction(){
        EntityManagerFactory entityManagerFactory = JpaUtils.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        Assertions.assertNotNull(entityTransaction);

        try{
            entityTransaction.begin();
            entityManager.persist(new Customer());
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        }finally {
            entityManager.close();
            entityManagerFactory.close();
        }

    }
}
