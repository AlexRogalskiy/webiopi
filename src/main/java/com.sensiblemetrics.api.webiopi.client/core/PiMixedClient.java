package com.sensiblemetrics.api.webiopi.client.core;

public class PiMixedClient extends PiClient {
    private PiHttpClient http;
    private PiCoapClient coap;

    private int tries = 0;
    private static final int MAX_TRIES = 3;

    public PiMixedClient(final String host) {
        super("", "", 0);
        this.http = new PiHttpClient(host);
        this.coap = new PiCoapClient(host);
    }

    public PiMixedClient(final String host,
                         final int httpPort,
                         final int coapPort) {
        super("", "", 0);
        this.http = new PiHttpClient(host, httpPort);
        this.coap = new PiCoapClient(host, coapPort);
    }

    @Override
    public void setCredentials(final String login,
                               final String password) {
        this.http.setCredentials(login, password);
        this.coap.setCredentials(login, password);
    }

    @Override
    public String sendRequest(final String method,
                              final String path) throws Exception {
        if (this.tries < MAX_TRIES) {
            final String response = coap.sendRequest(method, path);
            if (response != null) {
                this.tries = 0;
                return response;
            }
            this.tries++;
        }
        return this.http.sendRequest(method, path);
    }
}
