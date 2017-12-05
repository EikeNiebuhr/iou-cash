package iou.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
	@ManyToMany(mappedBy="friends")
	private Set<User> userFriends = new HashSet<User>();
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

	@JsonProperty
	public int getId() {
		return id;
	}

	@JsonProperty
	public void setId(int id) {
		this.id = id;
	}
}
