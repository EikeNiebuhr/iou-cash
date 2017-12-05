package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import iou.api.PersonResponse;
import iou.api.UserService;
import iou.models.Person;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/friends")
@Produces(MediaType.APPLICATION_JSON)
public class FriendsResource {

    private UserService userService;

    @Inject
    public FriendsResource(UserService userService)
    {
        this.userService = userService;
    }

    @GET
    @UnitOfWork
    public Set<PersonResponse> list()
    {
        return userService.getFriends(2);
    }

    @POST
    @UnitOfWork
    @Path("/create")
    public Response create(@PathParam("name") String name)
    {
        userService.createFriend(2, name, "Doe");
        return Response.ok().build();

    }

}
