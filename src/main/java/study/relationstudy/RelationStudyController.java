package study.relationstudy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class RelationStudyController {


    public void relationTest() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            TeamRelationEntity team = new TeamRelationEntity();
            team.setName("TeamA");
            //team.getMembers().add(member);
            em.persist(team);

            MemberRelationEntity member = new MemberRelationEntity();
            member.setUsername("member1");
            // 연관관계의 주인에게 값을 넣음
            member.changeTeam(team);
            em.persist(member);

            team.addMember(member);




            //DB 를 commit() 하기 전에 SQL DB에 실행
            em.flush();
            // 영속성컨텍스트를 지우기 때문에 아래 find() 는 DB 에서 가지고 올 수 있도록 함
            em.clear();

            MemberRelationEntity findMember = em.find(MemberRelationEntity.class, member.getId());
            List<MemberRelationEntity> members = findMember.getTeam().getMembers();

            for (MemberRelationEntity member1 : members) {
                System.out.println("m = " + member.getUsername());

            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();





     }




}
