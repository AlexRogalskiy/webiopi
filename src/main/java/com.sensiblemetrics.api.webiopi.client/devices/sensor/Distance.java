package com.sensiblemetrics.api.webiopi.client.devices.sensor;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class Distance extends Device {

    public Distance(final PiClient client,
                    final String deviceName) {
        super(client, deviceName, "sensor");
    }

    public float getMillimeter() {
        return Float.parseFloat(this.sendRequest("GET", "/distance/mm"));
    }
}
