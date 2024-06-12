package org.example;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.example.message.MQTTSender;

@SpringBootApplication
@EnableWebSecurity
public class DemoApplication {

	public static void main(String[] args) throws MqttException {

		SpringApplication.run(DemoApplication.class, args);
	}

}
