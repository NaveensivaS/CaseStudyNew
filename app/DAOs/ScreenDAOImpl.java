package DAOs;

import DTOs.Screen;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.function.Function;

@Singleton
@Transactional
public class ScreenDAOImpl {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function) {
        return this.jpaApi.withTransaction(function);
    }

    public Screen getScreenByMultiplexAndScreenNumber(Integer multiplexID, Integer screenNumber) {
        Screen screen = null;
        try {
            screen = this.wrap(entityManager -> entityManager.createQuery("select s from Screen s where s.multiplex.id=:multiplexID and s.ScreenNumber=:screenNumber ", Screen.class)
                    .setParameter("multiplexID", multiplexID).setParameter("screenNumber", screenNumber).getSingleResult());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return screen;
    }

    public void delete(Integer movieID, Integer multiplexID, Integer screenNumber) {
        this.wrap(entityManager ->
                entityManager.createQuery("Delete from Screen s where s.movie.id=:movieID and s.multiplex.id=:multiplexID and s.ScreenNumber=:screenNumber")
                        .setParameter("movieID", movieID)
                        .setParameter("multiplexID", multiplexID)
                        .setParameter("screenNumber", screenNumber)
                        .executeUpdate()
        );
    }

    public Screen add(Screen screen) {
        return this.wrap(eM -> {
            if (screen.getId() == null) {
                eM.persist(screen);
            } else {
                eM.merge(screen);
            }
            return screen;
        });
    }
}
