package com.sensiblemetrics.api.webiopi.client;

import com.sensiblemetrics.api.webiopi.client.core.PiClient;
import com.sensiblemetrics.api.webiopi.client.core.PiHttpClient;
import com.sensiblemetrics.api.webiopi.client.devices.analog.ADC;
import com.sensiblemetrics.api.webiopi.client.devices.analog.DAC;
import com.sensiblemetrics.api.webiopi.client.devices.analog.PWM;
import com.sensiblemetrics.api.webiopi.client.devices.digital.GPIO;
import com.sensiblemetrics.api.webiopi.client.devices.digital.NativeGPIO;
import com.sensiblemetrics.api.webiopi.client.devices.sensor.Temperature;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application {

    public static void main(final String[] args) {
        final String host = "192.168.1.234";
        final PiClient client = new PiHttpClient(host, PiHttpClient.DEFAULT_PORT);
//		final PiClient client = new PiCoapClient(host, PiCoapClient.DEFAULT_PORT);
//		final PiClient client = new PiMixedClient(host, PiHttpClient.DEFAULT_PORT, PiCoapClient.DEFAULT_PORT);
//		final PiClient client = new PiMulticastClient(PiMulticastClient.DEFAULT_PORT);
        client.setCredentials("webiopi", "raspberry");

        final Temperature temp0 = new Temperature(client, "temp0");
        log.info(temp0.getCelsius() + "°C");

        final NativeGPIO gpio = new NativeGPIO(client);
        final GPIO gpio0 = new GPIO(client, "gpio0");
        final GPIO gpio2 = new GPIO(client, "gpio2");
        gpio.setFunction(25, GPIO.OUT);
        gpio0.setFunction(0, GPIO.OUT);
        gpio2.setFunction(12, GPIO.OUT);

        final DAC dac = new DAC(client, "dac1");
        final ADC adc = new ADC(client, "adc0");
        final PWM pwm = new PWM(client, "pwm0");

        boolean value = true;
        for (int i = 0; i <= 100; i++) {
            gpio.digitalWrite(25, value);
            gpio0.digitalWrite(0, value);
            gpio2.digitalWrite(12, value);

            dac.writeFloat(0, (float) (i / 100.0));
            log.info("" + (adc.readFloat(1) * 3.3) + "V");
            pwm.writeAngle(7, i - 50);
            value = !value;
        }
    }
}
