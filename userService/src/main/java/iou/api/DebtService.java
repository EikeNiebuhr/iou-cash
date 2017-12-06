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

    public Debt find(int id) {
    	return debtDAO.find(id);
    }

    public void createDebt(int user_id, int friend_id, double amount)
    {
        User user = (User) personDAO.find(user_id);
        Person friend = personDAO.find(friend_id);
        Debt debt = new Debt(user, friend, amount);
        ((User) personDAO.find(user_id)).getDebts().add(debt);
        debtDAO.createOrUpdate(debt);
    }

    public void createAsset(int user_id, int friend_id, double amount)
    {
        User user = (User) personDAO.find(user_id);
        Person friend = personDAO.find(friend_id);
        Debt debt = new Debt(friend, user , amount);
        ((User) personDAO.find(user_id)).getAssets().add(debt);
        debtDAO.createOrUpdate(debt);
    }

    public Set<DebtResponse> getDebts(int user_id)
    {
        Set<DebtResponse> newSet = new HashSet<>();
        Set<Debt> debts =  ((User) personDAO.find(user_id)).getDebts();
        for (Debt debt: debts)
        {
            newSet.add(new DebtResponse(debt));
        }
        return newSet;
    }

    public Set<DebtResponse> getAssets(int user_id)
    {
        Set<DebtResponse> newSet = new HashSet<>();
        Set<Debt> assets =  ((User) personDAO.find(user_id)).getAssets();
        for (Debt asset: assets)
        {
            newSet.add(new DebtResponse(asset));
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

}
