package com.sensiblemetrics.api.webiopi.client.devices.analog;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;

public class DAC extends ADC {

    public DAC(final PiClient client,
               final String deviceName) {
        super(client, deviceName);
    }

    public DAC(final PiClient client,
               final String deviceName,
               final String type) {
        super(client, deviceName, type);
    }

    public float writeFloat(final int channel,
                            final float value) {
        return Float.parseFloat(this.sendRequest("POST", "/" + channel + "/float/" + value));
    }

    public float writeVolt(final int channel,
                           final float value) {
        return Float.parseFloat(this.sendRequest("POST", "/" + channel + "/volt/" + value));
    }
}
