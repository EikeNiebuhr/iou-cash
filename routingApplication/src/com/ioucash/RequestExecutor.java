package com.ioucash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class RequestExecutor {
    String execute(RequestBuilder requestBuilder) throws IOException {

        switch (requestBuilder.getMethod()) {
            case "GET":
                return this.executeGetRequest(requestBuilder);
//            case "POST":
//                return this.executePostRequest(requestBuilder);
//            case "UPDATE":
//                return this.executeUpdateRequest(requestBuilder);
//            case "DELETE":
//                return this.executeDeleteRequest(requestBuilder);
            default:
                System.out.println("FUCK OFF!");
                break;
        }

        return "nope";
    }

    private void executeDeleteRequest(RequestBuilder requestBuilder)
    {

    }

    private void executeUpdateRequest(RequestBuilder requestBuilder)
    {

    }

    private void executePostRequest(RequestBuilder requestBuilder)
    {

    }

    private String executeGetRequest(RequestBuilder requestBuilder) throws IOException {
        String query = requestBuilder.getFormatedUrlAsString();
        URLConnection connection = new URL(requestBuilder.getUrl() + "?" +  query).openConnection();
        connection.setRequestProperty("Accept-Charset", requestBuilder.getCharset());
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String output;
        StringBuilder response = new StringBuilder();

        while ((output = in.readLine()) != null) {
            response.append(output);
        }
        in.close();

        System.out.println(response.toString());
        //printing result from response
        return response.toString();
    }
}
