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
	/**
	 * 
	 */
	private static final long serialVersionUID = 8573375993213673137L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "person_friends",
			joinColumns = { @JoinColumn(name = "person_id") },
			inverseJoinColumns = { @JoinColumn(name = "friend_id") }
	)
	protected Set<Person> friends = new HashSet<Person>();

	@JsonIgnore
	@OneToMany(mappedBy="debitor", fetch = FetchType.EAGER)
	protected Set<Debt> debts = new HashSet<Debt>();

	@JsonIgnore
	@OneToMany(mappedBy="creditor", fetch = FetchType.EAGER)
	protected Set<Debt> assets = new HashSet<Debt>();
	
	@JsonProperty("name")
	protected String firstName;
	protected String lastName;
	protected String mailAddress;
	protected String street;
	protected Integer postcode;
	protected String location;
	
	public Person()
	{
		
	}
	
	public Person(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty
	public int getId() {
		return id;
	}

	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}

	@JsonProperty
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@JsonProperty
	public String getFirstName()
	{
		return firstName;
	}

	@JsonProperty
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@JsonProperty
	public String getLastName()
	{
		return lastName;
	}

	@JsonProperty
	public String getMailAddress()
	{
		return mailAddress;
	}

	@JsonProperty
	public void setMailAddress(String mailAddress)
	{
		this.mailAddress = mailAddress;
	}

	@JsonProperty
	public String getStreet()
	{
		return street;
	}

	@JsonProperty
	public void setStreet(String street)
	{
		this.street = street;
	}

	@JsonProperty
	public int getPostcode()
	{
		return postcode;
	}

	@JsonProperty
	public void setPostcode(int postcode)
	{
		this.postcode = postcode;
	}

	@JsonProperty
	public String getLocation()
	{
		return location;
	}

	@JsonProperty
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public Set<Person> getFriends()
	{
		return friends;
	}
	
	public void addFriend(Person friend)
	{
		assert friend != null;
		friends.add(friend);
	}

	public void removeFriend(Person friend)
	{
		assert friends.contains(friend);
		for (Debt debt: debts)
		{
			assert debt.isPayed() || debt.getCreditor() != friend;
		}
		for (Debt asset: assets)
		{
			assert asset.isPayed() || asset.getDebitor() != friend;
		}
		friends.remove(friend);
	}
	
	public Set<Debt> getDebts()
	{
		return debts;
	}
	
	public void addDebt(Debt debt)
	{
		assert debt != null;
		debts.add(debt);
	}
	
	public Set<Debt> getAssets()
	{
		return assets;
	}
	
	public void addAsset(Debt asset)
	{
		assert asset != null;
		assets.add(asset);
	}
}
