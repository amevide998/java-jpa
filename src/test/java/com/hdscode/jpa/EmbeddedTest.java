package com.hdscode.jpa;

import com.hdscode.jpa.entity.Member;
import com.hdscode.jpa.entity.Name;
import com.hdscode.jpa.util.JpaUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmbeddedTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp(){
        this.entityManagerFactory = JpaUtils.getEntityManagerFactory();
    }

    @Test
    void embeddedTest(){
        EntityManager entityManager = this.entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        // delete from db
        entityManager.createQuery("delete from Member where email = 'amevide@example.com'").executeUpdate();

        // create in db

        Member member = new Member();
        member.setEmail("amevide@example.com");

        Name name = new Name();
        name.setTitle("mr");
        name.setFirstName("amevide");
        name.setMiddleName("amevide");
        name.setLastName("amevide");
        member.setName(name);

        entityManager.persist(member);

        // cek in db
        Member dbMember = entityManager.find(Member.class, member.getId());
        Assertions.assertEquals(member.getId(), dbMember.getId());
        Assertions.assertEquals(member.getName().getFirstName(), dbMember.getName().getFirstName());
        Assertions.assertEquals(member.getName().getMiddleName(), dbMember.getName().getMiddleName());
        Assertions.assertEquals(member.getName().getLastName(), dbMember.getName().getLastName());
        Assertions.assertEquals(member.getName().getTitle(), dbMember.getName().getTitle());



        entityTransaction.commit();
        entityManager.close();

    }
}
