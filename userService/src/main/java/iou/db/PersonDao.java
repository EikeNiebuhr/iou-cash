package iou.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iou.models.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import iou.models.Person;

import javax.inject.Inject;


public class PersonDao extends AbstractDAO<Person> {

    @Inject
    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @UnitOfWork
    public void createOrUpdate(Person person) throws HibernateException  {
    	persist(person);
    }
    
    public void delete(Person person) {
    	currentSession().delete(person);
    }
    
	@SuppressWarnings("unchecked")
	@UnitOfWork
	public List<Person> findAll()
    {
    	return currentSession().getNamedQuery("Person.findAll").getResultList();
    }

    public List<Person> findAllFriends(int user_id)
    {
        User user = currentSession().find(User.class, user_id);
        List<Person> list = new ArrayList<Person>();

        for(Person item : user.getFriendships()) {
            list.add(item);
            System.out.println(item.getFirstName());
        }
        return list;
    }
}
