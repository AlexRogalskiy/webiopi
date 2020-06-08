package com.sensiblemetrics.api.webiopi.client.devices.sensor;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class Temperature extends Device {

    public Temperature(final PiClient client,
                       final String deviceName) {
        super(client, deviceName, "sensor");
    }

    public float getCelsius() {
        return Float.parseFloat(this.sendRequest("GET", "/temperature/c"));
    }

    public float getFahrenheit() {
        return Float.parseFloat(this.sendRequest("GET", "/temperature/f"));
    }
}
