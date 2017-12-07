package iou.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Person")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //Least normalisation strategy
@DiscriminatorColumn(
		name="type",
		discriminatorType=DiscriminatorType.STRING
)

@NamedQuery(name="Person.findAll", query="SELECT person FROM Person person") 
public class Person implements Serializable
{

	private static final long serialVersionUID = 8573375993213673137L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

    @ManyToMany(mappedBy="friends")
    private Set<Person> userFriends = new HashSet<Person>();

	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "person_friends",
			joinColumns = { @JoinColumn(name = "person_id") },
			inverseJoinColumns = { @JoinColumn(name = "friend_id") }
	)
	protected Set<Person> friends = new HashSet<Person>();

	@OneToMany(mappedBy="debitor", fetch = FetchType.EAGER)
	protected Set<Debt> debts = new HashSet<Debt>();

	@OneToMany(mappedBy="creditor", fetch = FetchType.EAGER)
	protected Set<Debt> assets = new HashSet<Debt>();
	

	public String firstName;
	public String lastName;
	protected String mailAddress;
	protected String street;
	protected Integer postcode;
	protected String location;

	public Person()
	{
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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

	public Set<Person> getFriends()
	{
		return friends;
	}

	public Set<Debt> getDebts()
	{
		return debts;
	}

	public Set<Debt> getAssets()
	{
		return assets;
	}

}
