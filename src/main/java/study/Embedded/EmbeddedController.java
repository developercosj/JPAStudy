package study.Embedded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbeddedController {

    public void embeddedMethod() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Address address = new Address("city", " street", "10000");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);

            em.persist(member);

            // 값을 복사해서 사용하게 되면 공유 참조로 인해 발생하는 부작용을 피할 수 있음
            Address address2 = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(address2);

            em.persist(member2);

            // Address 객체를 복사시 member 만 바꿨으나 member2에도 영향을 줌
            // 불변객체를 만들면 아래처럼 수정이 불가하게 되서 안정성이 생김
            //member.getHomeAddress().setCity("newCity");




            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();




    }

}
