package com.hdscode.jpa;

import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityManagerFactoryTest {

    @Test
    void create(){
        EntityManagerFactory entityManagerFactory = JpaUtils.getEntityManagerFactory();
        Assertions.assertNotNull(entityManagerFactory);
    }
}
