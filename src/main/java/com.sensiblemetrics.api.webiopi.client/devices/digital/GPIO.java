package com.sensiblemetrics.api.webiopi.client.devices.digital;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.devices.common.Device;

public class GPIO extends Device {
    public final static String OUT = "OUT";
    public final static String IN = "IN";

    public GPIO(final PiClient client,
                final String deviceName) {
        super(client, deviceName, null);
    }

    public String getFunction(final int channel) {
        return this.sendRequest("GET", "/" + channel + "/function");
    }

    public String setFunction(final int channel,
                              final String function) {
        return this.sendRequest("POST", "/" + channel + "/function/" + function);
    }

    public boolean digitalRead(final int channel) {
        final String res = this.sendRequest("GET", "/" + channel + "/value");
        return res.equals("1");
    }

    public boolean digitalWrite(final int channel,
                                final boolean value) {
        final String res = this.sendRequest("POST", "/" + channel + "/value/" + (value ? "1" : "0"));
        return res.equals("1");
    }
}
