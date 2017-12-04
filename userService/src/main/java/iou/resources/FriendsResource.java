package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import iou.api.UserService;
import iou.models.Person;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
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
    public List<Person> list()
    {
        return userService.getAllFriends(2);
    }



}
