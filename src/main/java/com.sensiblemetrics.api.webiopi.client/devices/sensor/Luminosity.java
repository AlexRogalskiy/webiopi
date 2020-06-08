package com.sensiblemetrics.api.webiopi.client.devices.sensor;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class Luminosity extends Device {

    public Luminosity(final PiClient client,
                      final String deviceName) {
        super(client, deviceName, "sensor");
    }

    public float getLux() {
        return Float.parseFloat(this.sendRequest("GET", "/luminosity/lux"));
    }
}
