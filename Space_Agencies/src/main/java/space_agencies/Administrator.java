package space_agencies;

import space_agencies.topic.TopicListener;
import space_agencies.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

public class Administrator {
    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("Administrator start");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        new Thread(new TopicListener("space.admin")).start();

        TopicWriter writer = new TopicWriter();

        Map<String, String> availableReceivers = new HashMap<>();
        availableReceivers.put("agencies", "space.admin.agencies");
        availableReceivers.put("carriers", "space.admin.carriers");
        availableReceivers.put("all", "space.admin.all");

        while (true) {
            String line = br.readLine();
            if (line.equals("EXIT"))
                break;
            String[] arguments = line.split(":", 2);
            if (arguments.length == 2) {
                String command = arguments[0];
                String data = arguments[1];
                Optional.ofNullable(availableReceivers.get(command)).ifPresentOrElse(topic -> {
                            try {
                                String message = "ADMIN:-1:" + data;
                                writer.send(topic, message);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }, () ->
                                System.out.println("No Receiver found")
                );
            } else
                System.out.println("Wrong format. Acceptable format: receiver:message");
        }
        System.exit(0);
    }
}
