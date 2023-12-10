package org.example;

import tests.relation.RelationTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        RelationTest relationTest = new RelationTest();
        relationTest.relationTest();

         /*  EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
     try {

            Member member = new Member();
            member.setId(1L);
            member.setName("A");
            member.setRoleType(RoleType.USER);
            // insert
            //Member member = new Member();
            //member.setId(1L);
            //member.setName("memberName");

            //em.persist(member);

            // select
            Member findMember = em.find(Member.class, 1L);

            // update
            // 컬렉션을 관리하는 것처럼 생각할것
            // JPA 를 통해서 객체를 가지고 오면 트랜잭션을 커밋하는 시점에 변경사항을 확인하여 변경사항은 update 해줌
            findMember.setName("HelloJpa");

            // delete
            //em.remove(findMember);

            // JPQL 사용
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(8)
                    .getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();


    */

    }
}