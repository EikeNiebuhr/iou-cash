package com.ioucash;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class FriendHandler implements HttpHandler {
    private static final String USER_AGENT = "Mozilla/5.0";

    private final RequestExecutor requestExecutor;

    FriendHandler(RequestExecutor requestExecutor) {
        this.requestExecutor = requestExecutor;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        RequestBuilder requestBuilder = RequestBuilder.builder(
                "https://eike-niebuhr.de",
                "UTF-8"
        ).withRequestProperty(
                "Accept-Charset",
                "UTF-8"
        ).withResponseHeaders(
                200
        ).build();

        String r = this.requestExecutor.execute(requestBuilder);

        httpExchange.sendResponseHeaders(200, r.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(r.getBytes());
        outputStream.close();
    }

}
