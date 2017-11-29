package iou.models;

import java.util.ArrayList;

public class User extends Person
{
	private String username, password;
	private ArrayList<Person> friends = new ArrayList<Person>();
	private ArrayList<Debt> debts = new ArrayList<Debt>();
	
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
	
	public ArrayList<Debt> getDebts()
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
