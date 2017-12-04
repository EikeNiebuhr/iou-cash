package iou.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //Least normalisation strategy
@DiscriminatorColumn(
		name="type",
		discriminatorType=DiscriminatorType.STRING
)

@NamedQuery(name="Person.find", query="SELECT person FROM Person person WHERE id = :person_id") 
@NamedQuery(name="Person.findAll", query="SELECT person FROM Person person") 
public class Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8573375993213673137L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	@ManyToMany(mappedBy="friendships")
	private Set<User> friends = new HashSet<User>();
	protected String firstName;
	protected String lastName;
	protected String mailAddress;
	protected String street;
	protected Integer postcode;
	protected String location;
	
	public Person()
	{
		
	}
			
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getMailAddress()
	{
		return mailAddress;
	}
	
	public void setMailAddress(String mailAddress)
	{
		this.mailAddress = mailAddress;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public int getPostcode()
	{
		return postcode;
	}

	public void setPostcode(int postcode)
	{
		this.postcode = postcode;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}
}
