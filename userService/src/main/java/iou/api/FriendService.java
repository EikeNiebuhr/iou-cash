package iou.api;

import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Person;
import iou.models.User;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

import static sun.security.krb5.SCDynamicStoreConfig.getConfig;

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

    public void remove(Person p) {
        Set<Person> set =  ((User) personDAO.find(2)).getFriends();
        Predicate<Person> personPredicate = person-> person.getId() == p.getId();
        set.removeIf(personPredicate);
    }

    public void update(Person p) {
        personDAO.createOrUpdate(p);
    }
}
