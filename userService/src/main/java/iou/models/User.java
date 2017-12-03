package iou.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.OneToMany;

@DiscriminatorValue("User")
public class User extends Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3669663879014464566L;
	
	private String username;
	private String password;
	@OneToMany(mappedBy="Person")
	private List<Person> friends = new ArrayList<>();
	private List<Debt> debts = new ArrayList<>();
	private List<Debt> assets = new ArrayList<>();
	
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
	
	public void addFriend(Person friend)
	{
		assert friend != null && friend != this && !friends.contains(friend);
		friends.add(friend);
	}

	public void deleteFriend(Person friend)
	{
		assert friends.contains(friend);
		friends.remove(friend);
	}
	
	public List<Debt> getDebts()
	{
		return debts;
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
