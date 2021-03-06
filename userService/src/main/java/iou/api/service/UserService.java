package iou.api.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import iou.api.response.PersonResponse;
import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Person;
import iou.models.User;

public class UserService {

    private PersonDAO personDAO;
    private DebtDAO debtDAO;

    public UserService(PersonDAO personDAO, DebtDAO debtDAO)
    {
        this.personDAO = personDAO;
        this.debtDAO = debtDAO;
    }

	public Person find(int id)
	{
		return personDAO.find(id);
	}

    public void createFriend(int user_id, Person person)
    {
    	personDAO.createOrUpdate(person);
    	User user = (User) personDAO.find(user_id);
    	user.getFriends().add(person);
    	personDAO.createOrUpdate(user);
    }

    public void createAsset(int user_id, int friend_id, double amount)
    {
    	User user = (User) personDAO.find(user_id);
    	Person friend = personDAO.find(friend_id);
    	Debt asset = new Debt(user, friend, amount);
    	((User) personDAO.find(user_id)).getAssets().add(asset);
    	debtDAO.createOrUpdate(asset);
    }

    public void payDebt(int debt_id)
    {
    	Debt debt = debtDAO.find(debt_id);
    	debt.pay();
    	debtDAO.createOrUpdate(debt);
    }

    public Set<PersonResponse> getFriends(int user_id)
    {
    	Set<Person> set =  ((User) personDAO.find(user_id)).getFriends();
    	Set<PersonResponse> newSet = new HashSet<>();
        for (Person item : set
             ) {
            newSet.add(new PersonResponse(item));
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

    public Set<Debt> getAssets(int user_id)
    {
    	return ((User) personDAO.find(user_id)).getAssets();
    }

    public Set<Debt> getOpenAssets(int user_id)
    {
    	Set<Debt> openAssets = new HashSet<Debt>();
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (!asset.isPayed())
    		{
        		openAssets.add(asset);
    		}
    	}
    	return openAssets;
    }



    public double getTotalAssetAmount(int user_id)
    {
    	double totalAssetAmount = 0;
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (!asset.isPayed())
    		{
        		totalAssetAmount += asset.getAmount();
    		}
    	}
    	return totalAssetAmount;
    }

    public double getTotalAmount(int user_id)
    {
    	double totalAmount = 0;
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		if (!debt.isPayed())
    		{
    			totalAmount -= debt.getAmount();
    		}
    	}
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (!asset.isPayed())
    		{
        		totalAmount += asset.getAmount();
    		}
    	}
    	return totalAmount;
    }

    public double getTotalDebtAmount(int user_id, int friend_id)
    {
    	double totalDebtAmount = 0;
    	Person friend = personDAO.find(friend_id);
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		if (debt.getCreditor() == friend && !debt.isPayed())
    		{
        		totalDebtAmount += debt.getAmount();
    		}
    	}
    	return totalDebtAmount;
    }

    public double getTotalAssetAmount(int user_id, int friend_id)
    {
    	double totalAssetAmount = 0;
    	Person friend = personDAO.find(friend_id);
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (asset.getDebitor() == friend && !asset.isPayed())
    		{
        		totalAssetAmount += asset.getAmount();
    		}
    	}
    	return totalAssetAmount;
    }

    public double getTotalAmount(int user_id, int friend_id)
    {
    	double totalAmount = 0;
    	Person friend = personDAO.find(friend_id);
    	Set<Debt> debts = ((User) personDAO.find(user_id)).getDebts();
    	for (Debt debt: debts)
    	{
    		if (debt.getCreditor() == friend && !debt.isPayed())
    		{
        		totalAmount += debt.getAmount();
    		}
    	}
    	Set<Debt> assets = ((User) personDAO.find(user_id)).getAssets();
    	for (Debt asset: assets)
    	{
    		if (asset.getDebitor() == friend && !asset.isPayed())
    		{
        		totalAmount += asset.getAmount();
    		}
    	}
    	return totalAmount;
    }

	public void update(User user) {
    	personDAO.createOrUpdate(user);
	}

	public Set<User> getUsers() {
		return new HashSet<>(personDAO.findAllUsers());
	}

	public void delete(User user) {
    	personDAO.delete(user);
	}
}