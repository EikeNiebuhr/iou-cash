package iou.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="Debt")
@NamedQuery(name="Debt.find", query="SELECT debt FROM Debt debt WHERE id = :debt_id") 
@NamedQuery(name="Debt.findAll", query="SELECT debt FROM Debt debt") 
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
	@ManyToOne(optional=false)
	@JoinColumn(name="creditor_id", referencedColumnName="id")
	private Person creditor;
//    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Person.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "person", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "debitor")})
	@ManyToOne(optional=false)
	@JoinColumn(name="debitor_id",referencedColumnName="id")
	private Person debitor;
	private double amount;
	private Date date;
	private boolean isPayed = false;
	
	public Debt(Person creditor, Person debitor, double amount)
	{
		assert creditor != null && debitor != null && amount > 0;
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

	public Person getDebitor() {
		return debitor;
	}

	public void setDebitor(Person debitor) {
		this.debitor = debitor;
	}

	public Person getCreditor() {
		return creditor;
	}

	public void setCreditor(Person creditor) {
		this.creditor = creditor;
	}
}
