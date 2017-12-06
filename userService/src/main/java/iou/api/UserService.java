package iou.api;

import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Person;

public class UserService {

    private PersonDAO personDAO;
    private DebtDAO debtDAO;

    public UserService(PersonDAO personDAO, DebtDAO debtDAO)
    {
        this.personDAO = personDAO;
        this.debtDAO = debtDAO;
    }

    public Person find(int id)
	{
		return personDAO.find(id);
	}
}