package iou;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import iou.models.Debt;
import iou.models.Guest;
import iou.models.Person;
import iou.models.User;

public class UserServiceApplication extends Application<UserServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new UserServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "UserService";
    }

    @Override
    public void initialize(final Bootstrap<UserServiceConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernate);
    }

    private final HibernateBundle<UserServiceConfiguration> hibernate = new HibernateBundle<UserServiceConfiguration>(Debt.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(UserServiceConfiguration configuration) {
            return configuration.getDatabaseAppDataSourceFactory();
        }
    };

    @Override
    public void run(final UserServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
