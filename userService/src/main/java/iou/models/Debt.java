package iou.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="Debt")
public class Debt implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5917879569351288576L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="id")
	private int id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Person.class, fetch = FetchType.EAGER)
    @JoinTable(name = "person", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "creditor")})
	private Person creditor;
//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Person.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "person", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "debitor")})
//	private Person debitor;
	private double amount;
	private Date date;
	private boolean isPayed = false;


	public Debt()
	{
	}
	
	public Debt(Person friend, double amount)
	{
		assert friend != null;
		this.amount = amount;
		//date = new Date(System.currentTimeMillis());
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
