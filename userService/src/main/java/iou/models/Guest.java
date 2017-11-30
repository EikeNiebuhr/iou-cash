package iou.models;

import javax.persistence.DiscriminatorValue;

@DiscriminatorValue("GUEST")
public class Guest extends Person
{
	public Guest(String firstName, String lastName)
	{
		assert firstName != null && firstName != "" && lastName != null && lastName != "";
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
