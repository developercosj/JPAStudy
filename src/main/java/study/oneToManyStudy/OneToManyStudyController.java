package study.oneToManyStudy;

import org.example.domain.Order;
import org.example.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToManyStudyController {


    // 일대다 단방향
    // Member, Team 객체 insert SQL 생성 후 team 에서 add(member) 를 해준것에 대한 Member 의 Fk 를 update 해주는 쿼리가 한번 더 작성됨
    // 실무에서 쓰지 않음
    public void OneToMany() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);


            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member);
            em.persist(team);



        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();





    }

}
