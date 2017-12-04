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
//    @OneToMany(cascade = CascadeType.ALL, targetEntity = Person.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "person", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "id")})
//	private List<Person> friends = new ArrayList<>();
/*    @OneToMany(cascade = CascadeType.ALL, targetEntity = Debt.class, fetch = FetchType.EAGER)
    @JoinTable(name = "debt", joinColumns = {@JoinColumn(name = "creditor")}, inverseJoinColumns = {@JoinColumn(name = "id")})*/
	@OneToMany(mappedBy="debitor")
    private Set<Debt> debts = new HashSet<Debt>();

//    @OneToMany(cascade = CascadeType.ALL, targetEntity = Debt.class, fetch = FetchType.EAGER)
//    @JoinTable(name = "debt", joinColumns = {@JoinColumn(name = "debitor")}, inverseJoinColumns = {@JoinColumn(name = "id")})

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
	
//	public void addFriend(Person friend)
//	{
//		assert friend != null && friend != this && !friends.contains(friend);
//		friends.add(friend);
//	}
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
}
