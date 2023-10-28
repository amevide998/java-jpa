package com.hdscode.jpa;

import com.hdscode.jpa.entity.Image;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class LobTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp(){
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory();
    }

    @Test
    void testLob() throws IOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();

        // delete
        entityManager.createQuery("delete from Image").executeUpdate();

        // create image
        Image image = new Image();
        image.setName("logo bangunkota");
        image.setDescription("logo bangun kota ");

        byte[] bytes = Files.readAllBytes(Path.of(Objects.requireNonNull(getClass().getResource("/images/logo.jpg")).getPath()));

        image.setImage(bytes);

        entityManager.persist(image);

        // cek in db

        Image dbImage = entityManager.createQuery("select i from Image i ", Image.class).getSingleResult();

        Assertions.assertEquals(image.getName(), dbImage.getName());
        Assertions.assertEquals(image.getDescription(), dbImage.getDescription());
        Assertions.assertNotNull(dbImage.getImage());


        entityTransaction.commit();
        entityManager.close();

    }
}
