package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;

import iou.api.service.UserService;
import iou.models.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Produces(MediaType.APPLICATION_JSON)
@Path("/users")
public class UserResource {


    private UserService userService;

    @Inject
    public UserResource(UserService userService)
    {
        this.userService = userService;
    }

    @POST
    @UnitOfWork
    public Response create(User user)
    {
        try {
            userService.update(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @PATCH
    @UnitOfWork
    public Response update(User user)
    {
        try {
            userService.update(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @UnitOfWork
    public Set<User> list()
    {
        return this.userService.getUsers();
    }

    @DELETE
    @UnitOfWork
    public Response delete(User user)
    {
        try {
            userService.delete(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
