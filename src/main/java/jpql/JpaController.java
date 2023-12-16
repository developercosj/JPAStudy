package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaController {

    public void testJpql() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUserName("member1");
            member.setAge(10);
            em.persist(member);

            // 타입 정보 명확할때 TypedQuery<객체> 로 받음
            TypedQuery<String> query1 = em.createQuery("select m.userName from Member m ", String.class);
            // 타입정보가 명확하지 않을때 Query 타입으로 받음
            Query query2 = em.createQuery("select m.userName, m.age from Member m ");


            // 반환값이 컬렉션
            // 값이 없으면 빈 리스트 반환
            TypedQuery<Member> query3 = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = query3.getResultList();
            // 값이 1개일때
            // 값이 없으면 NoResultException
            // 값이 2개 이상이어도 NonUniqueResultException
            TypedQuery<Member> query4 = em.createQuery("select m from Member m Where m.age = 100 ", Member.class);
            Member findMember = query4.getSingleResult();

            // 바인딩(체이닝으로)
            Member bindingMember = em.createQuery("select m from Member m Where m.userName = :userName ", Member.class)
                    .setParameter("userName", "member1")
                    .getSingleResult();

            // 타입을 모를때 Object 로 받음
            List resultObjectList = em.createQuery("select m.userName, m.age from Member m")
                    .getResultList();
            Object o = resultObjectList.get(0);
            Object[] result = (Object[]) o;

            // 더 간단하게
            List<Object[]> simpleObjectList = em.createQuery("select m.userName, m.age from Member m")
                    .getResultList();

            // 값을 DTO 로 조회
            List<MemberDTO> memberDTOList = em.createQuery("select new jpql.MemberDTO( m.age, m.userName) from Member m", MemberDTO.class)
                    .getResultList();
            // 페이징
            List<Member> pagingList = em.createQuery("select m from Member m Order by m.age desc", Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();





            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();




    }


}
