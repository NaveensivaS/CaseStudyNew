package DAOs;

import DTOs.Movie;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;

@Singleton
@Transactional
public class MovieDAOImpl {
    @Inject
    private JPAApi jpaApi;

    // wrap this.jpaApi.withTransaction : to make it reusable
    private <T> T wrap(Function<EntityManager, T> function) {
        return this.jpaApi.withTransaction(function);
    }

    public Movie insertOrUpdate(Movie movie) {

        return this.wrap(eM -> {
            if (movie.getId() == null) {
                eM.persist(movie);
            } else {
                eM.merge(movie);
            }
            return movie;
        });
    }

    public List<Movie> list() {
        return this.wrap(entityManager -> entityManager.createQuery("select m from Movie m", Movie.class).getResultList());
    }

    public List<Movie> getNotAllottedMovie(Integer MovieID) {
        return this.wrap(entityManager -> entityManager.createQuery("select m from Movie m where m.id!=:MovieID", Movie.class).setParameter("MovieID", MovieID).getResultList());
    }

    public void delete(Integer id) {
        this.wrap(entityManager ->
                entityManager.createQuery("delete from Movie m where m.id=" + id).executeUpdate()
        );
    }

    public Movie getByID(Integer id) {
        return this.wrap(entityManager -> entityManager.createQuery("select m from Movie m where m.id=" + id , Movie.class).getSingleResult());
    }

    public List<Movie> find(String searchString) {
        return this.wrap(entityManager -> entityManager.createQuery("select s from Movie s where lower(s.movieName) like lower(:searchString) ", Movie.class)
                .setParameter("searchString", "%" + searchString + "%").getResultList());
    }
}
