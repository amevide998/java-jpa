package com.hdscode.jpa;

import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityManagerTest {

    @Test
    void create(){
        EntityManagerFactory entityManagerFactory = JpaUtils.getEntityManagerFactory();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Assertions.assertNotNull(entityManager);

        entityManager.close();
        entityManagerFactory.close();
    }
}
