package space_agencies.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class TopicListener implements Runnable {
    private final String queueName;
    private final Channel channel;

    public TopicListener(String routingKey) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        this.channel = connection.createChannel();

        String EXCHANGE_NAME = "space";
        this.channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        this.queueName = this.channel.queueDeclare().getQueue();
        this.channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
    }

    @Override
    public void run() {
        DefaultConsumer consumer = new DefaultConsumer(this.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("Receive: \"" + msg + "\"");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        try {
            this.channel.basicConsume(this.queueName, false, consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
