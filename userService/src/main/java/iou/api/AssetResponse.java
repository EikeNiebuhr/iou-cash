package iou.api;

import iou.models.Debt;

import java.util.Date;

public class AssetResponse {

    public int id;
    public String name;
    public double amount;
    public Date date;
    public boolean isPayed = false;

    public AssetResponse()
    {

    }

    public AssetResponse(Debt debt)
    {
        this.id = debt.getId();
        this.name = debt.getDebitor().getFirstName() + " " + debt.getDebitor().getLastName();
        this.amount = debt.getAmount();
        this.date = debt.getDate();
        this.isPayed = debt.isPayed();
    }
}
