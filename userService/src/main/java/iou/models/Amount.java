package iou.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AMOUNT")
public class Amount implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5917879569351288576L;
	
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


	public Amount()
	{
	}
	
	public Amount(Person friend, double amount)
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
