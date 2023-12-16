package study.Embedded;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmbeddedController {

    public void testEmbeddedValue() {

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


    public void testValueTypeCollection() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("member1");
            // member 테이블 컬럼에 추가
            member.setHomeAddress(new Address("city1", "street", "0000"));

            // 새로운 테이블 생성 (인자가 스트링)
            member.getFavoriteFoods().add("샤브샤브");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("도넛");

            // 새로운 테이블 생성 (인자가 객체)
            member.getAddressHistory().add(new Address("city1", "street1", "0202"));
            member.getAddressHistory().add(new Address("city1", "street2", "0203"));

            em.persist(member);

            em.flush();
            em.clear();

            // 지연로딩이기 때문에 값타입 컬렉션은 조회전
            Member findMember = em.find(Member.class, member.getId());

            // 절대로 set 으로 쓰지 말것 다른 객체에도 의도치 않은 변경 발생 가능
            // findMember.getHomeAddress().setCity("newCity");

            // 이렇게 완전히 교체해서 수정해야함
            Address addr = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity", addr.getStreet(), addr.getZipcode()));

            // 스트링 값타입 컬렉션 교체 샤브샤브 -> 삼겹살
            // 컬렉션의 값이 변경되면 JPA 가 알아서 DB 변경시켜줌 ( 영속성 전이처럼 보임)
            findMember.getFavoriteFoods().remove("샤브샤브");
            findMember.getFavoriteFoods().add("삼겹살");

            // 객체 값타입 컬렉션 교체
            // 컬렉션
            // 해시코드, equals 구현 꼭 해야함 (컬렉션다룰때 중요)
            // remove 할때 Member 관련된 데이터를 모두 다 지우고 다시 넣음 (위에 원래 넣고 지우지 않은 데이터도 삭제하고 다시 넣음)
            findMember.getAddressHistory().remove(new Address("city1", "street1", "0202"));
            findMember.getAddressHistory().add(new Address("newCity1", "street1", "0202"));


            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();




    }

}
