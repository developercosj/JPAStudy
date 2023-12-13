package study.extend;

import org.example.domain.Order;
import org.example.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class ExtendController {


    public void joinTest() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Movie movie = new Movie();
            movie.setDirector("AA");
            movie.setActor("BB");
            movie.setName("오징어게임");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            // 1차 캐시 x
            em.find(Movie.class, movie.getId());

            tx.commit();
        } catch(Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
