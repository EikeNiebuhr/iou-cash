package com.ioucash;

import com.sun.deploy.net.URLEncoder;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder {

    private String url;
    private String charset;
    private String method = "GET";
    private Map<String, String> parameters;

    private RequestBuilder(Builder builder) {
        url = builder.url;
        charset = builder.charset;
        parameters = builder.parameters;

    }

    public String getUrl() {
        return this.url;
    }

    public String getCharset() {
        return this.charset;
    }

    public String getMethod() {
        return this.method;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }

    public String getFormatedUrlAsString()
    {
        String url = "";
        for (Map.Entry<String, String> entry : this.parameters.entrySet())
        {
            url += "?";
            url += entry.getKey();
            url += "=";
            url += entry.getValue();
        }

        return url;
    }


    public static Builder builder(String url, String charset) {
        return new Builder(
                url,
                charset
        );
    }

    public static class Builder {
        private String url;
        private String charset;
        private String method;
        private Map<String, String> parameters = new HashMap<>();
        private int responseCode;
        private HashMap<String, String> requestProperty = new HashMap<>();
        private String body;

        Builder(String url, String charset) {
            this.url = url; //mandatory
            this.charset = charset; //mandatory
        }

        public Builder withMethod(String method) {
            this.method = method; //default GET
            return this;
        }

        public Builder withParameters(
                String key,
                String value
        ) throws UnsupportedEncodingException {

            this.parameters.put(
                    key,
                    URLEncoder.encode(
                            value,
                            this.charset
                    )
            );

            return this;
        }

        public Builder withResponseHeaders(
                int responseCode
        ) {
            this.responseCode = responseCode;
            return this;
        }

        public Builder withRequestProperty(String attributeName, String attributeValue) {
            this.requestProperty.put(
                    attributeName,
                    attributeValue
            );

            return this;
        }

        public RequestBuilder build() {
            return new RequestBuilder(this);
        }
    }
}
