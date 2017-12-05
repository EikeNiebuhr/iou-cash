package iou.db;

import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Debt;
import iou.models.Person;

import java.util.List;

import javax.inject.Inject;

public class DebtDAO extends AbstractDAO<Debt> {
	
	@Inject
	public DebtDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@UnitOfWork
	public void createOrUpdate(Debt debt) throws HibernateException {
		persist(debt);
	}
    
	@SuppressWarnings("unchecked")
	@UnitOfWork
	public List<Person> findAll()
    {
    	return currentSession().getNamedQuery("Debt.findAll").getResultList();
    }
	
    public Debt find(int debt_id) {
    	return get(debt_id);
    }
}