package com.ioucash;

import com.sun.net.httpserver.HttpHandler;

class ContextFactory {

    private static final String CREATE_FRIEND_ROUTE = "/createFriend";
    private static final String DELETE_DEBT_ROUTE = "/deleteDebt";
    private static final String UPDATE_DEBT_ROUTE = "/updateDebt";
    private static final String GET_TOTAL_AMOUNT_ROUTE = "/totalAmount";

    private final HandlerFactory handlerFactory;

    ContextFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    ContextBuilder createFriendContext()
    {
        return new ContextBuilder(
                CREATE_FRIEND_ROUTE,
                this.handlerFactory.createFriendHandler()
        );
    }
//
//    ContextBuilder createDeleteDebtContext() {
//        return new ContextBuilder(
//                DELETE_DEBT_ROUTE,
//                this.handlerFactory.createDeleteDebtHandler()
//        );
//    }
//
//    ContextBuilder createUpdateDebtContext() {
//        return new ContextBuilder(
//                UPDATE_DEBT_ROUTE,
//                this.handlerFactory.createUpdateDebtHandler()
//        );
//    }
//
//    ContextBuilder createTotalAmountContext() {
//        return new ContextBuilder(
//                GET_TOTAL_AMOUNT_ROUTE,
//                this.handlerFactory.createTotalAmountHandler()
//        );
//    }
}
