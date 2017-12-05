package iou.api;


import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
import java.util.Set;

public class AssetService {

    private DebtDAO debtDAO;
    private PersonDAO personDAO;

    public AssetService(DebtDAO debtDAO, PersonDAO personDAO )
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


    public void createAsset(int user_id, int friend_id, double amount)
    {
        User user = (User) personDAO.find(user_id);
        Person friend = personDAO.find(friend_id);
        Debt debt = new Debt(friend, user , amount);
        ((User) personDAO.find(user_id)).getAssets().add(debt);
        debtDAO.createOrUpdate(debt);
    }

    public Set<DebtResponse> getAssets(int user_id)
    {
        Set<Debt> set =  ((User) personDAO.find(user_id)).getAssets();
        Set<DebtResponse> newSet = new HashSet<>();
        for (Debt item : set
                ) {
            newSet.add(new DebtResponse(item));
        }
        return newSet;
    }
}
