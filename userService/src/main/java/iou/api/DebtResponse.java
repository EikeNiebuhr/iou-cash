package iou.api;

import iou.models.Debt;
import iou.models.Person;

import java.util.Date;

public class DebtResponse {

    public int id;
    public PersonResponse creditor;
    public PersonResponse debitor;
    public double amount;
    public Date date;
    public boolean isPayed = false;

    public DebtResponse()
    {

    }

    public DebtResponse(Debt debt)
    {

    }
}

