package iou;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import iou.api.DebtService;
import iou.api.FriendService;
import iou.api.UserService;
import iou.db.DebtDAO;
import iou.db.PersonDAO;
import iou.models.Debt;
import iou.models.Guest;
import iou.models.Person;
import iou.models.User;
import iou.resources.DebtResource;
import iou.resources.PersonResource;
import iou.resources.FriendResource;

public class UserServiceApplication extends Application<UserServiceConfiguration> {

	public static void main(final String[] args) throws Exception {
		new UserServiceApplication().run(args);
	}

	@Override
	public String getName() {
		return "UserServiceApplication";
	}

	@Override
	public void initialize(final Bootstrap<UserServiceConfiguration> bootstrap) {
		// TODO: application initialization
		bootstrap.addBundle(hibernate);
	}

	private final HibernateBundle<UserServiceConfiguration> hibernate = new HibernateBundle<UserServiceConfiguration>(
			Debt.class, Person.class, User.class, Guest.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(UserServiceConfiguration configuration) {
			return configuration.getDatabaseAppDataSourceFactory();
		}
	};

    @UnitOfWork
	@Override
	public void run(final UserServiceConfiguration configuration, Environment environment) throws ClassNotFoundException {
		final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());
		final DebtDAO debtDAO = new DebtDAO(hibernate.getSessionFactory());
		final UserService userService = new UserService(personDAO, debtDAO);
		final FriendService friendService = new FriendService(personDAO, debtDAO);
		final DebtService debtService = new DebtService(debtDAO, personDAO);
        environment.jersey().register(new PersonResource(personDAO, debtDAO));
        environment.jersey().register(new FriendResource(userService, friendService));
        environment.jersey().register(new DebtResource(userService, debtService));
	}

}
