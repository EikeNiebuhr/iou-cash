package iou.api;

import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
import java.util.Set;

public class DebtService {

    private DebtDAO debtDAO;
    private PersonDAO personDAO;

    public DebtService(DebtDAO debtDAO, PersonDAO personDAO )
    {
        this.debtDAO = debtDAO;
        this.personDAO = personDAO;
    }

    public void update(Debt debt) {
        debtDAO.createOrUpdate(debt);
    }

    public void delete(Debt debt) {
        debtDAO.delete(debt);
    }


    public void createDebt(int user_id, int friend_id, double amount)
    {
        User user = (User) personDAO.find(user_id);
        Person friend = personDAO.find(friend_id);
        Debt debt = new Debt(user, friend, amount);
        ((User) personDAO.find(user_id)).getDebts().add(debt);
        debtDAO.createOrUpdate(debt);
    }

    public Set<DebtResponse> getDebts(int user_id)
    {
        Set<Debt> set =  ((User) personDAO.find(user_id)).getDebts();
        Set<DebtResponse> newSet = new HashSet<>();
        for (Debt item : set
                ) {
            newSet.add(new DebtResponse(item));
        }
        return newSet;
    }

    public Set<Debt> getOpenDebts(int user_id)
    {
        Set<Debt> openDebts = new HashSet<Debt>();
        Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
        for (Debt debt: debts)
        {
            if (!debt.isPayed())
            {
                openDebts.add(debt);
            }
        }
        return openDebts;
    }

    public double getTotalDebtAmount(int user_id)
    {
        double totalDebtAmount = 0;
        Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
        for (Debt debt: debts)
        {
            if (!debt.isPayed())
            {
                totalDebtAmount += debt.getAmount();
            }
        }
        return totalDebtAmount;
    }
	
}
