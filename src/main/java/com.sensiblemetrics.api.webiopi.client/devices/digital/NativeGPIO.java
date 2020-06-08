package com.sensiblemetrics.api.webiopi.client.devices.digital;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;

public class NativeGPIO extends GPIO {

    public NativeGPIO(final PiClient client) {
        super(client, "");
        this.setPath("/GPIO");
    }
}
