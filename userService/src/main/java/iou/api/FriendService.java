package iou.api;

import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class FriendService {

    private PersonDAO personDAO;
    private DebtDAO debtDAO;

    public FriendService(PersonDAO personDAO, DebtDAO debtDAO)
    {
        this.personDAO = personDAO;
        this.debtDAO = debtDAO;
    }

    public void remove(Person p) {
    	Set<Person> set =  ((User) personDAO.find(2)).getFriends();
    	Predicate<Person> personPredicate = person-> person.getId() == p.getId();
    	set.removeIf(personPredicate);
    }

    public void update(Person p) {
    	personDAO.createOrUpdate(p);
    }

    //POST
    public void createFriend(int user_id, Person person)
    {
    	personDAO.createOrUpdate(person);
    	User user = (User) personDAO.find(user_id);
    	user.getFriends().add(person);
    	personDAO.createOrUpdate(user);
    }

    //GET
    public Set<FriendResponse> getFriends(int user_id)
    {
    	Set<FriendResponse> newSet = new HashSet<>();
    	Set<Person> friends = ((User) personDAO.find(user_id)).getFriends();
        for (Person friend: friends)
        {
            newSet.add(new FriendResponse(friend));
        }
        return newSet;
    }
    
    public Set<Person> getFriendsOfDebts(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		friends.add(debt.getCreditor());
    	}
    	return friends;
    }
    
    public Set<Person> getFriendsOfOpenDebts(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		if (!debt.isPayed())
    		{
        		friends.add(debt.getCreditor());
    		}
    	}
    	return friends;
    }

    public Set<Person> getFriendsOfAssets(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		friends.add(asset.getDebitor());
    	}
    	return friends;
    }

    public Set<Person> getFriendsOfOpenAssets(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (!asset.isPayed())
    		{
        		friends.add(asset.getDebitor());
    		}
    	}
    	return friends;
    }

    public Set<Person> getFriendsOfDebtsOrAssets(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		friends.add(debt.getCreditor());
    	}
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		friends.add(asset.getDebitor());
    	}
    	return friends;
    }

    public Set<Person> getFriendsOfOpenDebtsOrOpenAssets(int user_id)
    {
    	Set<Person> friends = new HashSet<Person>();
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		if (!debt.isPayed())
    		{
        		friends.add(debt.getCreditor());
    		}
    	}
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (!asset.isPayed())
    		{
        		friends.add(asset.getDebitor());
    		}
    	}
    	return friends;
    }
}
