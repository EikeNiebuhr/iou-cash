package iou.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="debt")
public class Debt implements Serializable
{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="debt_id")
	private int id;
	@Column(name="lender")
	private Person lender;
	@Column(name="borrower")
	private Person borrower;
	private double amount;
	private Date date;
	private boolean isPayed = false;


	private Debt()
	{

	}
	
	public Debt(Person friend, double amount)
	{
		assert friend != null;
		this.amount = amount;
		date = new Date(System.currentTimeMillis());
	}

	public void pay()
	{
		isPayed = true;
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


	public static void createDebt() {

	}

}
