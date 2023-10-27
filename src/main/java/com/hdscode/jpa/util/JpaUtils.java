package com.hdscode.jpa.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtils {

    private static EntityManagerFactory entityManagerFactory = null;

    public static EntityManagerFactory getEntityManagerFactory() {
        if(entityManagerFactory == null){
            entityManagerFactory = Persistence.createEntityManagerFactory("learnjpa");
        }
        return entityManagerFactory;
    }
}
