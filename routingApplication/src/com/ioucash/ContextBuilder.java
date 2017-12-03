package com.ioucash;

import com.sun.net.httpserver.HttpHandler;

class ContextBuilder {
    private String route;

    private HttpHandler handler;

    ContextBuilder(
            String route,
            HttpHandler httpHandler
    ) {
        this.route = route;
        this.handler = httpHandler;
    }

    String getRoute() {
        return route;
    }

    HttpHandler getHandler() {
        return handler;
    }

    // add what ever you want to build
}
