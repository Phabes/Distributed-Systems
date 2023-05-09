package server;


import java.io.InputStreamReader;
import java.util.Scanner;

public class DevicePrinter extends Thread {
    private final SmartHomeServantLocator servantLocator;

    public DevicePrinter(SmartHomeServantLocator servantLocator) {
        this.servantLocator = servantLocator;
    }

    public void run() {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        while (true) {
            String line = in.nextLine();
            if (line.equalsIgnoreCase("list")) {
                servantLocator.printDevices();
            } else {
                System.out.println("Invalid command. Available commands: list");
            }
        }
    }
}
