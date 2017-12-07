package iou.models;

import java.io.Serializable;
import javax.persistence.*;

@Entity

@org.hibernate.annotations.NamedQuery(name="User.findAll", query="SELECT p FROM Person p WHERE TYPE(p) = 'User'" )

@DiscriminatorValue("User")
public class User extends Person implements Serializable
{
	private static final long serialVersionUID = -3669663879014464566L;

	private String username;
	private String password;

	public User() {
	}

	public String getUsername()
	{
		return username;
	}


	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
