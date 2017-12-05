package iou.resources;


import io.dropwizard.hibernate.UnitOfWork;
import iou.db.DebtDAO;
import iou.db.PersonDAO;
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

    private PersonDAO personDao;
    private DebtDAO debtDao;

    public PersonResource(PersonDAO personDao, DebtDAO debtDao) {
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
        Person n = new Person();
        n.setFirstName("Max");
        n.setLastName("Muster");
        personDao.createOrUpdate(n);
        Person l = new Person();
        l.setFirstName("Alfred");
        l.setLastName("Boy");
        personDao.createOrUpdate(l);
        User u = new User();
        u.setFirstName("User");
        u.setLastName("Resu");
        u.getFriends().add(p);
        u.getFriends().add(n);
        u.getFriends().add(l);
        personDao.createOrUpdate(u);
        Debt d = new Debt(u, p, 10);
        debtDao.createOrUpdate(d);
        Debt de = new Debt(p, u, 100);
        debtDao.createOrUpdate(de);
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
