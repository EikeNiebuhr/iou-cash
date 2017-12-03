package com.ioucash;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

class ServerHandler {

    private final HttpServer server;

    ServerHandler() throws IOException {
        this.server = HttpServer.create(new InetSocketAddress(50000), 0);
    }

    void addContext(List<ContextBuilder> contextBuilders)
    {
        for (ContextBuilder contextBuilder : contextBuilders) {
            this.server.createContext(
                    contextBuilder.getRoute(),
                    contextBuilder.getHandler()
            );
        }
    }

    void start() {
        this.server.start();
    }
}
