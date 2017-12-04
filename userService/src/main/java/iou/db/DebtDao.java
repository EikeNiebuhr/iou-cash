package iou.db;

import io.dropwizard.hibernate.UnitOfWork;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Debt;

import javax.inject.Inject;

public class DebtDao extends AbstractDAO<Debt> {

	@Inject
	public DebtDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@UnitOfWork
	public void createOrUpdate(Debt debt) throws HibernateException {
		persist(debt);
	}
}