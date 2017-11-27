package iou.models;

import java.util.ArrayList;

public class User
{
	private String username, password;
	private String firstName, lastName;
	private String mailAddress;
	private String street;
	private Integer postcode;
	private String location;
	private ArrayList<User> friends = new ArrayList<User>();
	
	public User(String username, String password, String firstName, String lastName)
	{
		assert username != null && username != "" && password != null && password != "" && firstName != null && firstName != "" && lastName != null && lastName != "";
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getFirstName()
	{
		return firstName;
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

	public void addFriend(User friend)
	{
		assert friend != null && friend != this && !friends.contains(friend);
		friends.add(friend);
	}

	public void deleteFriend(User friend)
	{
		assert friends.contains(friend);
		friends.remove(friend);
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
