package iou.models;

import java.util.Date;

public class Debt
{
	private Person friend;
	private double amount;
	private Date date;
	private boolean isPayed = false;
	
	public Debt(Person friend, double amount)
	{
		assert friend != null;
		this.friend = friend;
		this.amount = amount;
		date = new Date(System.currentTimeMillis());
	}
	
	public void pay()
	{
		isPayed = true;
	}
	
	public Person getFriend()
	{
		return friend;
	}
	
	public double getAmount()
	{
		return amount;
	}	
	
	public Date getDate()
	{
		return date;
	}	
	
	public boolean getPayed()
	{
		return isPayed;
	}	
}
