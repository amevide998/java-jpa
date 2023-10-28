package com.hdscode.jpa;

import com.hdscode.jpa.entity.Category;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

public class CategoryTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp(){
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory();
    }

    @Test
    void testCategory(){
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete from db
        entityManager.createQuery("delete from Category").executeUpdate();

        Category category = new Category();
        category.setName("gadgets");
        category.setDescription("description");
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        entityManager.persist(category);

        // cek in db
        TypedQuery<Category> query = entityManager.createQuery("select c from Category c where c.name = 'gadgets'", Category.class);

        Category dbCategory = query.getResultList().get(0);

        Assertions.assertEquals(category.getName(), dbCategory.getName());
        Assertions.assertEquals(category.getDescription(), dbCategory.getDescription());
        Assertions.assertEquals(category.getCreatedAt(), dbCategory.getCreatedAt());

        entityTransaction.commit();
        entityManager.close();
    }
}
