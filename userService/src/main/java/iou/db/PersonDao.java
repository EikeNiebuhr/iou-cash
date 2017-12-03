package iou.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import iou.models.Person;


public class PersonDao extends AbstractDAO<Person> {

    public PersonDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
    
    public void createOrUpdate(Person person) throws HibernateException  {
    	currentSession().saveOrUpdate(person);
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
