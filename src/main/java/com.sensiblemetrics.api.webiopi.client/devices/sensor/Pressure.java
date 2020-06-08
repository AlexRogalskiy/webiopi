package com.sensiblemetrics.api.webiopi.client.devices.sensor;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class Pressure extends Device {

    public Pressure(final PiClient client,
                    final String deviceName) {
        super(client, deviceName, "sensor");
    }

    public float getHectoPascal() {
        return Float.parseFloat(this.sendRequest("GET", "/pressure/hpa"));
    }

    public float getPascal() {
        return Float.parseFloat(this.sendRequest("GET", "/pressure/pa"));
    }
}
