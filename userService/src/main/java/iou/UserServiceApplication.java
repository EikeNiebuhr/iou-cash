package iou;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import iou.db.PersonDao;
import iou.models.Debt;
import iou.models.Person;

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

    private final HibernateBundle<UserServiceConfiguration> hibernate = new HibernateBundle<UserServiceConfiguration>(Debt.class, Person.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(UserServiceConfiguration configuration) {
            return configuration.getDatabaseAppDataSourceFactory();
        }
    };

    @Override
    public void run(final UserServiceConfiguration configuration,
                    final Environment environment) {
    	final PersonDao dao = new PersonDao(hibernate.getSessionFactory());
    	
    	Person person = new Person();
    	person.setFirstName("asdasdasd");
    	dao.createOrUpdate(person);
    }

}
