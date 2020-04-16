package DAOs;

import DTOs.Screen;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

@Singleton
@Transactional
public class SearchDAOImpl {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function) {
        return this.jpaApi.withTransaction(function);
    }

    public List<Screen> getScreen(String movie, String Multiplex) {

        List<Screen> screen = null;
        try {
            screen = (List<Screen>) this.wrap(entityManager -> entityManager.createQuery("select s from Screen s, Movie M, Multiplex Mx where Mx.name =:multiplexID and M.movieName =:screenNumber ",
                    Screen.class)
                    .setParameter("multiplexID", Multiplex).setParameter("screenNumber", movie).getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return screen;
    }
}
