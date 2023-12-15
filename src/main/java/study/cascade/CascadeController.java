package study.cascade;

import org.example.domain.Order;
import org.example.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CascadeController {

    public void cascade() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            // cascade 를 설정하면 Parent 를 상속한(cascade = CascadeType.ALL) Child 또한 영속성 컨텍스트에서 관리됨
            em.persist(parent);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            // orphanRemoval 이 동작된다.
            // orphanRemoval = true 이 없으면 단지 영속성 컨텍스트에서 없어지는 것이지만 true 를 하면
            // delete from Child 쿼리가 동작
            findParent.getChildList().remove(0);



            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();




    }

}
