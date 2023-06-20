package space_agencies.queue;

import com.rabbitmq.client.*;
import space_agencies.topic.TopicWriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class QueueListener implements Runnable {
    private final String key;
    private final String carrierID;
    private final TopicWriter writer;
    private final Channel channel;

    public QueueListener(String key, String carrierID, TopicWriter writer) throws IOException, TimeoutException {
        this.key = key;
        this.carrierID = carrierID;
        this.writer = writer;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        this.channel = connection.createChannel();
        this.channel.basicQos(1);
    }

    @Override
    public void run() {
        Consumer consumer = new DefaultConsumer(this.channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, StandardCharsets.UTF_8);
                System.out.println("Receive: \"" + msg + "\"");
                String[] parts = msg.split(":", 3);
                if (parts.length == 3) {
                    String agencyID = parts[0];
                    String commissionID = parts[1];
                    String data = parts[2];
                    String message = carrierID + ":" + commissionID + ":" + "finished";
                    writer.send("space.agencies." + agencyID, message);
                    writer.send("space.admin", message);
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        try {
            this.channel.basicConsume(key, false, consumer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
