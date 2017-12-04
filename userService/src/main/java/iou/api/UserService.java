package iou.api;

import iou.db.DebtDao;
import iou.db.PersonDao;
import iou.models.Debt;
import iou.models.Person;

import java.util.List;
import java.util.Set;

public class UserService {

    private PersonDao personDao;
    private DebtDao debtDao;

    public UserService(PersonDao personDao, DebtDao debtDao)
    {
        this.personDao = personDao;
        this.debtDao = debtDao;
    }


    //POST
    public void createFriend(int user_id, String friend_name)
    {

    }

    public void deleteFriend(int friend_id)
    {

    }

    public void createDebt(int user_id, int friend_id, double amount)
    {

    }

    public void payDebt(int user_id, int debt_id)
    {

    }

    //GET
    public List<Person> getAllFriends(int user_id)
    {
        return personDao.findAllFriends(user_id);
    }

    //???
/*
    public Set<Person> getAllFriendsWithDebtsAndAssets(int user_id)
    {

    }

    //include user
    public Set<Debt> getAllDebts(int user_id)
    {

    }

    public Set<Debt> getAllAssets(int user_id)
    {

    }
*/



}

