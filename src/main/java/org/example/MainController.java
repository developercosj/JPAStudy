package org.example;

import org.example.domain.Order;
import org.example.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainController {

    public void exam1() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = new Order();
            order.addOrderItem(new OrderItem());




        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();




    }

}
