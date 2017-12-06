package iou.resources;

import java.net.URI;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import iou.api.FriendResponse;
import iou.api.FriendService;
import iou.api.PersonResponse;
import iou.api.UserService;
import iou.models.Person;
import org.glassfish.jersey.server.Uri;

@Path("/friends")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
//@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class FriendResource {

    private final FriendService friendService;
    private final UserService userService;

    @Inject
    public FriendResource(UserService userService, FriendService friendService) {
        this.userService = userService;
        this.friendService = friendService;
    }

    @GET
    @UnitOfWork
    public Set<FriendResponse> list() {
        return friendService.getFriends(2);
    }

    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Person p, @Context UriInfo uriInfo) {
        userService.createFriend(2, p);
        UriBuilder builder = uriInfo.getBaseUriBuilder();
        builder.path(Integer.toString(p.getId()));
        return Response.created(builder.build()).build(
        );
    }

    @PATCH
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") int id, @Context UriInfo uriInfo) {
        Person p = userService.find(id);
        friendService.update(p);
        UriBuilder builder = uriInfo.getBaseUriBuilder();
        builder.path(Integer.toString(p.getId()));
        return Response.ok(builder.build()).build(
        );
    }

    @DELETE
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        try {
            Person p = userService.find(id);
            friendService.remove(p);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
