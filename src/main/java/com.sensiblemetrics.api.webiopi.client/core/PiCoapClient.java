package com.sensiblemetrics.api.webiopi.client.core;

import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.coap.CoAP;
import org.eclipse.californium.core.coap.Request;

public class PiCoapClient extends PiClient {
    public final static int DEFAULT_PORT = 5683;
    private CoapClient client;

    public PiCoapClient(final String host) {
        this(host, DEFAULT_PORT);
    }

    public PiCoapClient(final String host,
                        final int port) {
        super("coap", host, port);
        this.client = new CoapClient();
    }

    @Override
    public String sendRequest(final String method,
                              final String path) throws Exception {
        Request request;
        if ("GET".equalsIgnoreCase(method)) {
            request = new Request(CoAP.Code.GET);
            request.setURI(this.urlBase + path);
        } else if ("POST".equalsIgnoreCase(method)) {
            request = new Request(CoAP.Code.POST);
            request.setURI(this.urlBase + path);
        } else throw new Exception("Method not supported: " + method);

        final CoapResponse response = this.client.advanced(request);
        if (response != null) {
            return response.getResponseText();
        }
        return null;
    }
}
