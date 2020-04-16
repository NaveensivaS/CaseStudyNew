package controllers;

import DTOs.Movie;
import DTOs.Screen;
import DTOs.Search;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.SearchService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SearchController extends Controller {

    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;
    @Inject
    SearchService searchService;

    public Result index(Http.Request request) {
        Form<Search> SearchForm = this.formFactory.form(Search.class);
        return ok(views.html.search.add.render(SearchForm,"Search Form",request,messagesApi.preferred(request)));
    }
    public Result addMovie(Http.Request request) {
        Form<Search> SearchForm = this.formFactory.form(Search.class).bindFromRequest(request);

        this.searchService.getScreen(SearchForm.get().getMovie(),SearchForm.get().getMultiplex());
        return ok(views.html.search.list.render((List<Screen>) searchService));
    }
}
