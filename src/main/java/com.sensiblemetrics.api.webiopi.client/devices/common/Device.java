package com.sensiblemetrics.api.webiopi.client.devices.common;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class Device {
    private PiClient client;
    private String path;

    public Device(final PiClient client, final String deviceName, final String type) {
        this.client = client;
        if (type != null) {
            this.path = "/devices/" + deviceName + "/" + type;
        } else {
            this.path = "/devices/" + deviceName;
        }
    }

    public String sendRequest(final String method,
                              final String subPath) {
        try {
            return this.client.sendRequest(method, this.path + subPath);
        } catch (Exception e) {
            log.error("Cannot send request with method={}, path={}, message: {}", method, this.path + subPath, e.getMessage());
            return null;
        }
    }
}
