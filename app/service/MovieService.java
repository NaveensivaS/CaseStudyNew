package service;

import DTOs.Movie;
import DAOs.MovieDAOImpl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class MovieService {
    @Inject
    MovieDAOImpl movieDAOImpl;

    public Movie addMovie(Movie movie) {
        return movieDAOImpl.insertOrUpdate(movie);
    }

    public List<Movie> getAllMovie() {
        return movieDAOImpl.list();
    }

    public List<Movie> getNotAllottedMovie(Integer MovieID) {
        return movieDAOImpl.getNotAllottedMovie(MovieID);
    }

    public void deleteByID(Integer id) {
        movieDAOImpl.delete(id);
    }

    public Movie getMovieByID(Integer id) {
        return movieDAOImpl.getByID(id);
    }
}
