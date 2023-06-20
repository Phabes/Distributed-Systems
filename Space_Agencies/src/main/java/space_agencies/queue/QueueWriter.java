package space_agencies.queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class QueueWriter {
    private final String queueName;
    private final Channel channel;

    public QueueWriter(String queueName) throws IOException, TimeoutException {
        this.queueName = queueName;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        this.channel = connection.createChannel();
        this.channel.queueDeclare(queueName, false, false, false, null);
    }

    public void send(String message) throws IOException {
        this.channel.basicPublish("", this.queueName, null, message.getBytes());
    }
}
