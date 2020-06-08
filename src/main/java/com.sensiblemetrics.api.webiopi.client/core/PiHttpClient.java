package com.sensiblemetrics.api.webiopi.client.core;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class PiHttpClient extends PiClient {
    public final static int DEFAULT_PORT = 8000;

    public PiHttpClient(final String host) {
        super("http", host, DEFAULT_PORT);
    }

    public PiHttpClient(final String host,
                        final int port) {
        super("http", host, port);
    }

    @Override
    public String sendRequest(final String method,
                              final String path) throws Exception {
        BufferedReader reader = null;
        try {
            final URL url = new URL(this.urlBase + path);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            if (this.auth != null) {
                connection.setRequestProperty("Authorization", this.auth);
            }

            // read the output from the server
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            log.error("Cannot send request with method={}, path={}, message={}", method, path, e.getMessage());
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        }
    }
}
