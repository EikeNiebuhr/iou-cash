package iou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity

@org.hibernate.annotations.NamedQuery(name="User.findAllFriends", query="SELECT p FROM Person p " )

@DiscriminatorValue("User")
public class User extends Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3669663879014464566L;
	
	private String username;
	private String password;
	
	public User() {
		
	}

	public User(String username, String password, String firstName, String lastName)
	{
		assert username != null && username != "" && password != null && password != "" && firstName != null && firstName != "" && lastName != null && lastName != "";
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public User(Guest guest, String username, String password)
	{
		assert username != null && username != "" && password != null && password != "";
		this.username = username;
		this.password = password;
		this.firstName = guest.firstName;
		this.lastName = guest.lastName;
		this.mailAddress = guest.mailAddress;
		this.street = guest.mailAddress;
		this.postcode = guest.postcode;
		this.location = guest.location;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public boolean changePassword(String oldPassword, String newPassword)
	{
		assert newPassword != null && newPassword != "";
		if (oldPassword.equals(password))
		{
			password = newPassword;
			return true;
		}
		else
		{
			return false;
		}
	}
}
