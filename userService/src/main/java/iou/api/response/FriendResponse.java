package iou.api.response;

import iou.models.Debt;
import iou.models.Person;

public class FriendResponse {

    public int id;
    public String name;
    public double totalDebt;
    public double totalAsset;


    public FriendResponse()
    {

    }

    public FriendResponse(Person p)
    {
        this.id = p.getId();
        this.name = p.getFirstName()+" "+p.getLastName();
        for (Debt asset : p.getAssets())
        {
            this.totalAsset+=asset.getAmount();
        }
        for (Debt item:p.getDebts()
             ) {
            this.totalDebt+=item.getAmount();
        }
    }
}
