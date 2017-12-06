package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import iou.api.DebtResponse;
import iou.api.DebtService;
import iou.api.UserService;
import iou.models.Debt;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/debts")
public class DebtResource {

    private UserService userService;
    private DebtService debtService;

    @Inject
    public DebtResource(UserService userService, DebtService debtService)
    {
        this.userService = userService;
        this.debtService = debtService;
    }

    @POST
    @UnitOfWork
    public Response create(Debt debt)
    {
        try {
            debtService.update(debt);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PATCH
    @UnitOfWork
    @Path("{id}")
    public Response update(@PathParam("id") int id)
    {
        try {
            Debt debt = debtService.find(id);
            debtService.update(debt);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }


    @GET
    @UnitOfWork
    public Set<DebtResponse> list()
    {
        return this.debtService.getDebts(2);
    }

    @DELETE
    @UnitOfWork
    @Path("{id}")
    public Response delete(@PathParam("id") int id)
    {
        try {
            Debt debt = debtService.find(id);
            debtService.delete(debt);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @UnitOfWork
    @Path("/total")
    public double getTotal()
    {
        return debtService.getTotalDebtAmount(2);
    }
}
