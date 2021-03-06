package iou.resources;

import io.dropwizard.hibernate.UnitOfWork;
import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Guest;
import iou.models.Person;
import iou.models.User;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class SystemResource {

    private PersonDAO personDao;
    private DebtDAO debtDao;

    public SystemResource(PersonDAO personDao, DebtDAO debtDao) {
        this.personDao = personDao;
        this.debtDao = debtDao;
    }
    @GET
    @UnitOfWork
    public String createData()
    {
        Person p = new Guest();
        p.setFirstName("Alfred");
        p.setLastName("Biolek");
        personDao.createOrUpdate(p);
        User u = new User();
        u.setFirstName("Argo");
        u.setLastName("TheMachine");
        u.getFriends().add(p);
        personDao.createOrUpdate(u);
        Person n = new Guest();
        n.setFirstName("Max");
        n.setLastName("Muster");
        personDao.createOrUpdate(n);
        Person l = new Guest();
        l.setFirstName("Alfredo");
        l.setLastName("Boyeee");
        personDao.createOrUpdate(l);
        Debt d = new Debt(u, p, 10);
        debtDao.createOrUpdate(d);
        Debt de = new Debt(p, u, 100);
        debtDao.createOrUpdate(de);
        Debt deb = new Debt(n, u, 200);
        debtDao.createOrUpdate(deb);
        u.getFriends().add(n);
        u.getFriends().add(l);
        personDao.createOrUpdate(u);
        return "WHOOP";
    }
}
