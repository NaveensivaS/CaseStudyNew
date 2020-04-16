package controllers;

import DTOs.Movie;
import DTOs.Multiplex;
import DTOs.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MovieService;
import service.MultiplexService;
import service.ScreenService;

import javax.inject.Inject;
import java.util.List;

public class AllotMovieController extends Controller {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    ScreenService screenService;
    @Inject
    MovieService movieService;
    @Inject
    MultiplexService multiplexService;

    public Result allotList(Http.Request request, Integer multiplexID, Integer screenNumber) {
        Screen screen = screenService.getScreenByMultiplexAndScreenNumber(multiplexID, screenNumber);
        if (screen == null) {
            screen = new Screen();
            screen.setMultiplex(new Multiplex(multiplexID));
            screen.setScreenNumber(screenNumber);
        }
        List<Movie> movies1 = movieService.getNotAllottedMovie(screen.getMovie() == null ? 0 : screen.getMovie().getId());
        return ok(views.html.allot.list.render(screen,movies1));
    }

    public Result allot(Http.Request request, Integer MovieID, Integer multiplexID, Integer screenNumber) {
        Screen screen = screenService.getScreenByMultiplexAndScreenNumber(multiplexID, screenNumber);
        if (screen == null) {
            screen = new Screen();
            screen.setMovie(new Movie(MovieID));
            screen.setMultiplex(new Multiplex(multiplexID));
            screen.setScreenNumber(screenNumber);

        }
        screenService.addScreen(screen);
        return redirect(routes.AllotMovieController.allotList(multiplexID, screenNumber));
    }

    public Result removeAllocation(Http.Request request, Integer MovieID, Integer multiplexID, Integer screenNumber) {
        screenService.delete(MovieID, multiplexID, screenNumber);
        return redirect(routes.AllotMovieController.allotList(multiplexID, screenNumber));
    }

    public Result listScreen(Http.Request request, Integer multiplexID) {
        Multiplex multiplexModel = multiplexService.getMultiplex(multiplexID);
        return ok(views.html.allot.listScreen.render(multiplexModel));
    }
}
