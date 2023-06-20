package space_agencies;

import space_agencies.queue.QueueWriter;
import space_agencies.topic.TopicListener;
import space_agencies.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class Agency {

    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Agency ID:");
        String agencyID = br.readLine();

        Map<String, QueueWriter> availableServices = new HashMap<>();
        availableServices.put("load", new QueueWriter("space.carriers.load"));
        availableServices.put("people", new QueueWriter("space.carriers.people"));
        availableServices.put("satellites", new QueueWriter("space.carriers.satellites"));

        TopicWriter writer = new TopicWriter();

        AtomicInteger commissionID = new AtomicInteger();

        new Thread(new TopicListener("space.agencies." + agencyID)).start();
        new Thread(new TopicListener("space.admin.agencies")).start();
        new Thread(new TopicListener("space.admin.all")).start();

        while (true) {
            String line = br.readLine();
            if (line.equals("EXIT"))
                break;
            String[] arguments = line.split(":", 2);
            if (arguments.length == 2) {
                String command = arguments[0];
                String data = arguments[1];
                Optional.ofNullable(availableServices.get(command)).ifPresentOrElse(serviceWriter -> {
                            try {
                                String message = agencyID + ":" + commissionID.get() + ":" + data;
                                serviceWriter.send(message);
                                writer.send("space.admin", message);
                                commissionID.getAndIncrement();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }, () ->
                                System.out.println("No service found")
                );
            } else
                System.out.println("Wrong format. Acceptable format: service:data");
        }
        System.exit(0);
    }
}
