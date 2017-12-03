package iou.resources;


import io.dropwizard.hibernate.UnitOfWork;
import iou.db.PersonDao;
import iou.models.Person;
import iou.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;


@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

    private PersonDao personDao;

    public PeopleResource(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GET
    @UnitOfWork
    public String createPerson()
    {
        Person p = new Person();
        p.setFirstName("A");
        p.setLastName("B");
        personDao.createOrUpdate(p);
        User u = new User();
        u.setFirstName("User");
        u.setLastName("Resu");
        personDao.createOrUpdate(u);
        return "WHOOP";
    }

//    @GET
//    @UnitOfWork
//    public List<Person> findByName(
//            @QueryParam("name") Optional<String> name
//    ) {
//        if (name.isPresent()) {
//            return personDao.findByName(name.get());
//        } else {
//            return personDao.findAll();
//        }
//    }
//
//    @GET
//    @Path("/{id}")
//    @UnitOfWork
//    public Optional<person> findById(@PathParam("id") LongParam id) {
//        return personDao.findById(id.get());
//    }
}
