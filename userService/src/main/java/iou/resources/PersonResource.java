package iou.resources;


import io.dropwizard.hibernate.UnitOfWork;
import iou.db.DebtDao;
import iou.db.PersonDao;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;


@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private PersonDao personDao;
    private DebtDao debtDao;

    public PersonResource(PersonDao personDao, DebtDao debtDao) {
        this.personDao = personDao;
        this.debtDao = debtDao;
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
        u.addFriend(p);
        personDao.createOrUpdate(u);
        Debt d = new Debt(u, p, 10);
        debtDao.createOrUpdate(d);
        return "WHOOP";
    }

    @GET
    @UnitOfWork
    @Path("/people/find")
    public List<Person> findByName(
            @QueryParam("name") Optional<String> name
    ) {
        if (name.isPresent()) {
            return null; }
            else {
            return personDao.findAll();
        }
    }
//
//    @GET
//    @Path("/{id}")
//    @UnitOfWork
//    public Optional<person> findById(@PathParam("id") LongParam id) {
//        return personDao.findById(id.get());
//    }
}
