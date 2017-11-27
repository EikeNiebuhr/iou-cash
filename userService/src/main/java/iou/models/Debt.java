package iou.models;

import java.util.Date;

public class Debt
{
	private User lender, borrower;
	private double amount;
	private Date date;
	private boolean isPayed = false;
	
	public Debt(User lender, User borrower, double amount)
	{
		assert lender != null && borrower != null && amount > 0;
		this.lender = lender;
		this.borrower = borrower;
		this.amount = amount;
		date = new Date(System.currentTimeMillis());
	}
	
	public void pay()
	{
		isPayed = true;
	}
	
	public User getLender()
	{
		return lender;
	}
	
	public User getBorrower()
	{
		return borrower;
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
