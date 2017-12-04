package iou.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import io.dropwizard.hibernate.UnitOfWork;
import iou.models.Person;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PersonDao extends AbstractDAO<Person> {
	
	private EntityManager entityManager;

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
    
    public Person find(Person person) {
    	return currentSession().find(Person.class, person);
    }
    
	@SuppressWarnings("unchecked")
	@UnitOfWork
	public List<Person> findAll() {
    	return currentSession().getNamedQuery("Person.findAll").getResultList();
    }
	
    public Person find(int person_id) {
    	Query query = entityManager.createNamedQuery("Person.find").setParameter("person_id", person_id);  
        return (Person) query.getSingleResult();
    }
}
