import org.apache.zookeeper.KeeperException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String connectString = "127.0.0.1:2182";
        int timeout = 3000;
        String node = "/z";
        String appName = "mspaint.exe";
        NodeWatcher nodeWatcher = new NodeWatcher(connectString, timeout, node, appName);
        nodeWatcher.run();
    }
}