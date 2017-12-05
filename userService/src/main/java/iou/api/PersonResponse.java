package iou.api;

import iou.models.Person;

public class PersonResponse {

    public int id;
    public String name;

    public PersonResponse()
    {

    }

    public PersonResponse(Person p)
    {
        this.id = p.getId();
        this.name = p.getFirstName() + " " + p.getLastName();

    }
}
