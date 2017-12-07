package iou.models;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Guest")
public class Guest extends Person
{
	private static final long serialVersionUID = 2617874744487332192L;

	public Guest() {
	}
}
