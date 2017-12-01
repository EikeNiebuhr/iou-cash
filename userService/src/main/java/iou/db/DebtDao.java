package iou.db;

import io.dropwizard.hibernate.AbstractDAO;
import iou.models.Debt;
import org.hibernate.SessionFactory;

public class DebtDao extends AbstractDAO<Debt> {

    public DebtDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
