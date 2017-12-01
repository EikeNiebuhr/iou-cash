package iou.db;

import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Debt;

public class DebtDao extends AbstractDAO<Debt> {

	public DebtDao(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}