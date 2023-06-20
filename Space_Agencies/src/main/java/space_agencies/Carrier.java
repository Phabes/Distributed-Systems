package space_agencies;

import space_agencies.queue.QueueListener;
import space_agencies.topic.TopicListener;
import space_agencies.topic.TopicWriter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class Carrier {
    public static void main(String[] args) throws IOException, TimeoutException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Carrier ID:");
        String carrierID = br.readLine();

        String firstService, secondService;
        List<String> availableServices = new ArrayList<>();
        availableServices.add("load");
        availableServices.add("people");
        availableServices.add("satellites");
        do {
            System.out.println("Available services: load, people, satellites. Choose two of them.");
            firstService = br.readLine();
            secondService = br.readLine();
        }
        while (!availableServices.contains(firstService) ||
                !availableServices.contains(secondService) ||
                firstService.equals(secondService));

        TopicWriter writer = new TopicWriter();

        new Thread(new QueueListener("space.carriers." + firstService, carrierID, writer)).start();
        new Thread(new QueueListener("space.carriers." + secondService, carrierID, writer)).start();
        new Thread(new TopicListener("space.admin.carriers")).start();
        new Thread(new TopicListener("space.admin.all")).start();

        while (true) {
            String line = br.readLine();
            if (Objects.equals(line, "EXIT"))
                break;
            else
                System.out.println("Available commands: EXIT");
        }
        System.exit(0);
    }
}
