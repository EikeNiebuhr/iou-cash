package iou.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="PERSON")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE) //Least normalisation strategy
@DiscriminatorColumn(
		name="PERSON_TYPE",
		discriminatorType=DiscriminatorType.STRING
)
public class Person implements Serializable
{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PERSON_ID")
	private int id;
	@Column(name="firstname")
	protected String firstName;
	@Column(name="lastname")
	protected String lastName;
	@Column(name="mail_address")
	protected String mailAddress;
	@Column(name="street")
	protected String street;
	@Column(name="postcode")
	protected Integer postcode;
	@Column(name="location")
	protected String location;



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
