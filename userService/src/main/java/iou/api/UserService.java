package iou.api;

import iou.db.DebtDao;
import iou.db.PersonDao;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
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
    public void createFriend(int user_id, String firstName, String lastName)
    {
    	Person person = new Person();
    	person.setFirstName(firstName);
    	person.setLastName(lastName);
    	((User) personDao.find(user_id)).getFriends().add(person);
    }

    public void deleteFriend(int user_id, int friend_id)
    {
    	((User) personDao.find(user_id)).getFriends().remove(personDao.find(friend_id));
    }

    public void createDebt(int user_id, int friend_id, double amount)
    {
    	User user = (User) personDao.find(user_id);
    	Person friend = personDao.find(friend_id);
    	Debt debt = new Debt(user, friend, amount);
    	((User) personDao.find(user_id)).getDebts().add(debt);
    }

    public void payDebt(int debt_id)
    {
    	debtDao.find(debt_id).pay();
    }

    //GET
    public Set<Person> getFriends(int user_id)
    {
    	return ((User) personDao.find(user_id)).getFriends();
    }
    
    public Set<Person> getFriendsWithDebts(int user_id)
    {
    	Set<Debt> debts = ((User) personDao.find(user_id)).getDebts();
    	Set<Person> friends = new HashSet<Person>();
    	for (Debt debt: debts)
    	{
    		friends.add(debt.getDebitor());
    	}
    	return friends;
    }

    public Set<Person> getFriendsWithAssets(int user_id)
    {
    	Set<Debt> debts = ((User) personDao.find(user_id)).getDebts();
    	Set<Person> friends = new HashSet<Person>();
    	for (Debt debt: debts)
    	{
    		friends.add(debt.getDebitor());
    	}
    	return friends;
    }

    public Set<Person> getFriendsWithDebtsOrAssets(int user_id)
    {
    	Set<Debt> debts = ((User) personDao.find(user_id)).getDebts();
    	Set<Person> friends = new HashSet<Person>();
    	for (Debt debt: debts)
    	{
    		friends.add(debt.getDebitor());
    	}
    	return friends;
    }

    //include user
    public Set<Debt> getDebts(int user_id)
    {
    	return ((User) personDao.find(user_id)).getDebts();
    }

    public Set<Debt> getAssets(int user_id)
    {
    	return ((User) personDao.find(user_id)).getAssets();
    }
}