package com.ioucash;

import com.sun.net.httpserver.HttpHandler;

class HandlerFactory {
    HttpHandler createFriendHandler() {
        return new FriendHandler(
                this.createRequestExecutor()
        );
    }

//    public HttpHandler createDeleteDebtHandler() {
//        return new FriendHandler();
//
//    }
//
//    public HttpHandler createUpdateDebtHandler() {
//        return new FriendHandler();
//
//    }
//
//    public HttpHandler createTotalAmountHandler() {
//        return new FriendHandler();
//
//    }
//
    private RequestExecutor createRequestExecutor()
    {
        return new RequestExecutor();
    }
}
