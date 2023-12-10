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
                member.setTeamId(team.getId());
                em.persist(member);

            MemberRelationTest findMember = em.find(MemberRelationTest.class, member.getId());

            Long findTeamId = findMember.getTeamId();
            TeamRelationTest findTeam = em.find(TeamRelationTest.class, findTeamId);


            tx.commit();


        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();





    }




}
