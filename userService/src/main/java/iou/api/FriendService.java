package iou.api;

import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
import java.util.Set;

public class FriendService {

    private PersonDAO personDAO;
    private DebtDAO debtDAO;

    public FriendService(PersonDAO personDAO, DebtDAO debtDAO)
    {
        this.personDAO = personDAO;
        this.debtDAO = debtDAO;
    }

    public Set<FriendResponse> getFriends(int user_id)
    {
        Set<Person> set =  ((User) personDAO.find(user_id)).getFriends();
        Set<FriendResponse> newSet = new HashSet<>();
        for (Person item : set
                ) {
            newSet.add(new FriendResponse(item));
        }
        return newSet;
    }
}
