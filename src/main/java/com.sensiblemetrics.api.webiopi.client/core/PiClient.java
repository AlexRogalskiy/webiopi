package com.sensiblemetrics.api.webiopi.client.core;

import lombok.Data;
import org.apache.commons.codec.binary.Base64;

@Data
public abstract class PiClient {
    protected String urlBase;
    protected String auth;

    public static String encodeCredentials(final String login,
                                           final String password) {
        return Base64.encodeBase64String((login + ":" + password).getBytes());
    }

    public PiClient(final String protocol,
                    final String host,
                    final int port) {
        this.urlBase = protocol + "://" + host + ":" + port;
    }

    public void setCredentials(final String login,
                               final String password) {
        this.auth = "Basic " + encodeCredentials(login, password);
    }

    public abstract String sendRequest(final String method,
                                       final String path) throws Exception;
}
