package userService;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import iou.UserServiceConfiguration;
import iou.db.PersonDao;
import iou.models.Debt;
import iou.models.Guest;
import iou.models.Person;
import iou.models.User;
//
//public class PersonTest {
//	private final HibernateBundle<UserServiceConfiguration> hibernate = new HibernateBundle<UserServiceConfiguration>(
//			Debt.class, Person.class, User.class, Guest.class) {
//		@Override
//		public DataSourceFactory getDataSourceFactory(UserServiceConfiguration configuration) {
//			return configuration.getDatabaseAppDataSourceFactory();
//		}
//	};
//
//	@Test
//	public void createPerson()
//	{
//        Person person = new Person();
//        person.setFirstName("Sebastian");
//        person.setLastName("Kurz");
//        PersonDao personDao = new PersonDao(hibernate.getSessionFactory());
//        personDao.createOrUpdate(person);
//        Person foundPerson = personDao.find(person);
//        assertEquals(person, foundPerson);
//	}
//}
