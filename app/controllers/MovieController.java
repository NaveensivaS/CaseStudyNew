package controllers;

import DTOs.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MovieService;

import javax.inject.Inject;

public class MovieController extends Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;
    @Inject
    MovieService movieService;

    public Result createMovie(Http.Request request) {
        Form<Movie> movieForm = this.formFactory.form(Movie.class);
        return ok(views.html.movie.addMovie.render(movieForm, "Add Movie", null, request, messagesApi.preferred(request)));
    }

    public Result editMovie(Http.Request request, Integer ID) {
        Form<Movie> movieForm = this.formFactory.form(Movie.class).fill(movieService.getMovieByID(ID));
        return ok(views.html.movie.addMovie.render(movieForm, "Edit Movie", ID, request, messagesApi.preferred(request)));
    }

    public Result delete(Http.Request request, Integer ID) {
        movieService.deleteByID(ID);
        return redirect(routes.MovieController.listMovie());
    }

    public Result addMovie(Http.Request request, Integer ID) {
        Form<Movie> movieForm = this.formFactory.form(Movie.class).bindFromRequest(request);

         this.movieService.addMovie(movieForm.get());
         return redirect(routes.MovieController.listMovie());
    }

    public Result listMovie(Http.Request request) {
        return ok(views.html.movie.list.render(this.movieService.getAllMovie()));
    }
}
