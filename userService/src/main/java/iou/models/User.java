package iou.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

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

	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_friends",
			joinColumns = { @JoinColumn(name = "user_id") },
			inverseJoinColumns = { @JoinColumn(name = "friend_id") }
	)
	private Set<Person> friends = new HashSet<Person>();

	@JsonIgnore
	@OneToMany(mappedBy="debitor")
    private Set<Debt> debts = new HashSet<Debt>();

	@JsonIgnore
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
	
	public Set<Person> getFriends()
	{
		return friends;
	}
	
	public void addFriend(Person friend)
	{
		assert friend != null;
		friends.add(friend);
	}

	@JsonProperty
	public void deleteFriend(Person friend)
	{
		assert friends.contains(friend);
		friends.remove(friend);
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
