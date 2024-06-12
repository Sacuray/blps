package org.example.message;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MQTTSender {
    String brokerUrlRpi_Mqtt = "tcp://localhost:1883";
    String clientId="MQTTClient";
    String topic = "mqtt";
    int qos=0;
    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";

    public void publish(String data) throws MqttPersistenceException, MqttException {

        /* Create a connection to MQTT plugin in RabbitMQ(MQTT Plugin listen on all interfaces on port 1883) */
        MqttClient client = new MqttClient(brokerUrlRpi_Mqtt,clientId);

        System.out.println("Publishing to topic \""+topic+"\" qos "+qos);

        /* Create MQQT Message */
        MqttMessage message = new MqttMessage(data.getBytes());
        message.setQos(qos);
        message.setRetained(false);

        /* Configure username and password */
        MqttConnectOptions connOpts = setUpConnectionOptions(USERNAME, PASSWORD);

        /* Connect to the MQTT Plugin */
        client.connect(connOpts);

        /* Publish the Message */
        System.out.println(message.toString());
        client.publish(topic, message);
    }

    private static MqttConnectOptions setUpConnectionOptions(String username, String password) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());
        return connOpts;
    }
}
