package iou.db;

import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Debt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class DebtDao extends AbstractDAO<Debt> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	public DebtDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@UnitOfWork
	public void createOrUpdate(Debt debt) throws HibernateException {
		persist(debt);
	}
	
    public Debt find(int debt_id) {
    	Query query = entityManager.createNamedQuery("Debt.find").setParameter("debt_id", debt_id);  
        return (Debt) query.getSingleResult();
    }
}