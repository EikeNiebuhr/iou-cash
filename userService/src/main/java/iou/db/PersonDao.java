package iou.db;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Person;
import org.hibernate.SessionFactory;

public class PersonDao extends AbstractDAO<Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
