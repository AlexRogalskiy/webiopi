package com.sensiblemetrics.api.webiopi.client.devices.analog;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class ADC extends Device {

    public ADC(final PiClient client,
               final String deviceName) {
        super(client, deviceName, "analog");
    }

    public ADC(final PiClient client,
               final String deviceName,
               final String type) {
        super(client, deviceName, type);
    }

    public float readFloat(int channel) {
        return Float.parseFloat(this.sendRequest("GET", "/" + channel + "/float"));
    }
}
