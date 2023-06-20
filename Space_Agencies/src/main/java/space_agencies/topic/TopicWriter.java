package space_agencies.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class TopicWriter {
    private final String EXCHANGE_NAME = "space";
    private final Channel channel;

    public TopicWriter() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        this.channel = connection.createChannel();

        this.channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
    }

    public void send(String topic, String message) throws IOException {
        this.channel.basicPublish(EXCHANGE_NAME, topic, null, message.getBytes(StandardCharsets.UTF_8));
    }

}
