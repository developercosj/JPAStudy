package tests.relation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class RelationTest {


    public void relationTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            TeamRelationTest team = new TeamRelationTest();
                team.setName("TeamA");
                em.persist(team);
            MemberRelationTest member = new MemberRelationTest();
                member.setUsername("member1");
                member.setTeam(team);
                em.persist(member);

                //DB 를 commit() 하기 전에 SQL DB에 실행
                em.flush();
                // 영속성컨텍스트를 지우기 때문에 아래 find() 는 DB 에서 가지고 올 수 있도록 함
                em.clear();

            MemberRelationTest findMember = em.find(MemberRelationTest.class, member.getId());

            TeamRelationTest findTeam = findMember.getTeam();
            System.out.println("findTeam = " + findTeam.getName());
            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();





    }




}
