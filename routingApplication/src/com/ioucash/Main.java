package com.ioucash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ServerHandler serverHandler =  new ServerHandler();
        HandlerFactory handlerFactory = new HandlerFactory();

        ContextFactory contextFactory = new ContextFactory(
                handlerFactory
        );

        List<ContextBuilder> contextBuilders = new ArrayList<>();

        contextBuilders.add(contextFactory.createFriendContext());
//        contextBuilders.add(contextFactory.createDeleteDebtContext());
//        contextBuilders.add(contextFactory.createUpdateDebtContext());
//        contextBuilders.add(contextFactory.createTotalAmountContext());

        serverHandler.addContext(
                contextBuilders
        );

        serverHandler.start();
    }
}
