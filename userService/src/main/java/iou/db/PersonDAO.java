package iou.db;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import iou.models.Person;

import javax.inject.Inject;

public class PersonDAO extends AbstractDAO<Person> {
	
    @Inject
    public PersonDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @UnitOfWork
    public void createOrUpdate(Person person) throws HibernateException  {
    	persist(person);
    }
    
    public void delete(Person person) {
    	currentSession().delete(person);
    }
    
    public Person find(Person person) {
    	return currentSession().find(Person.class, person);
    }
    
	@SuppressWarnings("unchecked")
	@UnitOfWork
	public List<Person> findAll()
    {
    	return currentSession().getNamedQuery("Person.findAll").getResultList();
    }
	
    public Person find(int person_id) {
        return get(person_id);
    }
}
