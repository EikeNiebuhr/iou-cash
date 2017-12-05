package iou.resources;

import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.dropwizard.hibernate.UnitOfWork;
import iou.api.FriendResponse;
import iou.api.FriendService;
import iou.api.PersonResponse;
import iou.api.UserService;
import iou.models.Person;

@Path("/friends")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class FriendsResource {

    private final FriendService friendService;
    private final UserService userService;

    @Inject
    public FriendsResource(UserService userService, FriendService friendService)
    {
        this.userService = userService;
        this.friendService = friendService;
    }

    @GET
    @UnitOfWork
    public Set<FriendResponse> list()
    {
        return friendService.getFriends(2);
    }

    @POST
    @UnitOfWork
    public Response create(String p)
    {
    	ObjectMapper mapper = new ObjectMapper();
    	Person person = mapper.convertValue(p, Person.class);
    	System.out.println(person.getFirstName());
        userService.createFriend(2, person.getFirstName(), "asd");
        return Response.ok().build();

    }

}
