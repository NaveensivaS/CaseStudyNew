package controllers;

import DTOs.Multiplex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.data.Form;
import play.data.FormFactory;
import play.i18n.MessagesApi;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.MultiplexService;

import javax.inject.Inject;

public class MultiplexController extends Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Inject
    FormFactory formFactory;
    @Inject
    MessagesApi messagesApi;
    @Inject
    MultiplexService multiplexService;

    public Result listMultiplex(Http.Request request) {
        return ok(views.html.multiplex.list.render(this.multiplexService.getAllMultiplex()));
    }

    public Result createMultiplex(Http.Request request) {
        Form<Multiplex> multiplexForm = this.formFactory.form(Multiplex.class);
        return ok(views.html.multiplex.add.render(multiplexForm, "Add Multiplex",null, request, messagesApi.preferred(request)));
    }

    public Result editMultiplex(Http.Request request, Integer ID) {
        Form<Multiplex> multiplexForm = this.formFactory.form(Multiplex.class).fill(multiplexService.getMultiplex(ID));
        return ok(views.html.multiplex.add.render(multiplexForm, "Edit Multiplex",ID, request, messagesApi.preferred(request)));
    }

    public Result delete(Http.Request request, Integer ID) {
        multiplexService.deleteByID(ID);
        return redirect(routes.MultiplexController.listMultiplex());
    }

    public Result addMultiplex(Http.Request request, Integer ID) {
        Form<Multiplex> multiplexForm = this.formFactory.form(Multiplex.class).bindFromRequest(request);
        if (multiplexForm.hasErrors()) {
            logger.error("errors = {}", multiplexForm.errors());
            request.flash().adding("failed", "Constraints not satisfied!!!");
            return badRequest(views.html.multiplex.add.render(multiplexForm, ID == null ? "Add Multiplex" : "Edit Multiplex",ID, request, messagesApi.preferred(request)));
        }
        multiplexForm.get().setId(ID);
        this.multiplexService.addOrUpdateMultiplex(multiplexForm.get());
        request.flash().adding("success", "Movie Added Successfully");
        return redirect(routes.MultiplexController.listMultiplex());
    }
}
