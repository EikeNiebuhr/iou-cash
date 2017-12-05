package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import iou.api.DebtResponse;
import iou.api.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/debts")
public class DebtResource {

    private UserService userService;

    @Inject
    public DebtResource(UserService userService)
    {
        this.userService = userService;
    }

    @POST
    @UnitOfWork
    public Response createDebt()
    {
        return Response.ok().build();
    }

    @GET
    @UnitOfWork
    public Set<DebtResponse> list()
    {
        return this.userService.getDebts(2);
    }

}
