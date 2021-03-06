package iou.api.response;

import iou.models.Debt;
import iou.models.Person;

import java.util.Date;

public class DebtResponse {

    public int id;
    public String name;
    public double amount;
    public Date date;
    public boolean isPayed = false;
    public String notice;

    public DebtResponse()
    {

    }

    public DebtResponse(Debt debt)
    {
        this.id = debt.getId();
        this.name = debt.getCreditor().getFirstName() + " " + debt.getCreditor().getLastName();
        this.amount = debt.getAmount();
        this.date = debt.getDate();
        this.isPayed = debt.isPayed();
        this.notice = debt.getNotice();
    }
}

