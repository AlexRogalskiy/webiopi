package com.sensiblemetrics.api.webiopi.client.devices.analog;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;

public class PWM extends DAC {

    public PWM(final PiClient client,
               final String deviceName) {
        super(client, deviceName, "pwm");
    }

    public float readAngle(final int channel) {
        return Float.parseFloat(this.sendRequest("GET", "/" + channel + "/angle"));
    }

    public float writeAngle(final int channel,
                            final float angle) {
        return Float.parseFloat(this.sendRequest("POST", "/" + channel + "/angle/" + angle));
    }
}
