package iou.userService.userService;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class userServiceApplication extends Application<userServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new userServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "userService";
    }

    @Override
    public void initialize(final Bootstrap<userServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final userServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
