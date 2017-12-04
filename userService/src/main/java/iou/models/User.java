package iou.models;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity


@DiscriminatorValue("User")
public class User extends Person implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3669663879014464566L;
	
	private String username;
	private String password;

	@ManyToMany(cascade = {CascadeType.ALL})
	@JoinTable(
			name = "user_friends",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "friend_id") }
	)
	private Set<Person> friendships = new HashSet<Person>();

	@OneToMany(mappedBy="debitor")
    private Set<Debt> debts = new HashSet<Debt>();

	@OneToMany(mappedBy="creditor")
    private Set<Debt> assets = new HashSet<Debt>();

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
		friendships.add(friend);

	}
//
//	public void deleteFriend(Person friend)
//	{
//		assert friends.contains(friend);
//		friends.remove(friend);
//	}

	public Set<Debt> getDebts()
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

	public Set<Person> getFriendships() {
		return friendships;
	}

	public void setFriendships(Set<Person> friendships) {
		this.friendships = friendships;
	}
}
