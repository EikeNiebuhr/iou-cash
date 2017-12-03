package iou.db;

import java.util.List;

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
	public List<Person> findAll() {
    	return currentSession().getNamedQuery("Person.findAll").getResultList();
    }
}
