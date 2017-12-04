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
	public List<Person> findAll()
    {
    	return currentSession().getNamedQuery("Person.findAll").getResultList();
    }
<<<<<<< HEAD
	
    public Person find(int person_id) {
    	Query query = entityManager.createNamedQuery("Person.find").setParameter("person_id", person_id);  
        return (Person) query.getSingleResult();
=======

    public List<Person> findAllFriends(int user_id)
    {
        User user = currentSession().find(User.class, user_id);
        List<Person> list = new ArrayList<Person>();

        for(Person item : user.getFriendships()) {
            list.add(item);
            System.out.println(item.getFirstName());
        }
        return list;
>>>>>>> branch 'master' of https://github.com/EikeNiebuhr/iou-cash
    }
}
