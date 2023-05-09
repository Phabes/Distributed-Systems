package server;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class Server {
    public static void main(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try {
            communicator = Util.initialize(args);

            SmartHomeServantLocator servantLocator = new SmartHomeServantLocator();

            ObjectAdapter adapter = communicator.createObjectAdapter("SmarthomeAdapter1");
//            ObjectAdapter adapter = communicator.createObjectAdapter("SmarthomeAdapter2");

            adapter.addServantLocator(servantLocator, "");

            adapter.activate();

            DevicePrinter devicePrinter = new DevicePrinter(servantLocator);
            devicePrinter.start();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();
        } catch (Exception e) {
            System.err.println(e);
            status = 1;
        } finally {
            if (communicator != null) {
                try {
                    communicator.destroy();
                } catch (Exception e) {
                    System.err.println(e);
                    status = 1;
                }
            }
        }
        System.exit(status);
    }

}
