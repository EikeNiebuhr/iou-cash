package iou.api.response;

import iou.api.response.DebtResponse;
import iou.models.Debt;
import iou.models.Person;

import java.util.HashSet;

public class PersonResponse {

    public int id;
    public String name;
    public HashSet<DebtResponse> debts = new HashSet<>();


    public PersonResponse()
    {

    }

    public PersonResponse(Person p)
    {
        this.id = p.getId();
        this.name = p.getFirstName() + " " + p.getLastName();
        for (Debt d : p.getDebts()) {
            this.debts.add(new DebtResponse(d));
        }
    }
}
