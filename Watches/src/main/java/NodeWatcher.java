import org.apache.zookeeper.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NodeWatcher implements Watcher {
    private final ZooKeeper zooKeeper;
    private Process process;
    private final String node;
    private final String appName;

    public NodeWatcher(String connectString, int timeout, String node, String appName) throws IOException {
        this.zooKeeper = new ZooKeeper(connectString, timeout, this);
        this.node = node;
        this.appName = appName;
    }

    public void run() throws InterruptedException, KeeperException, IOException {
        this.zooKeeper.addWatch(this.node, AddWatchMode.PERSISTENT_RECURSIVE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String command = reader.readLine();
            if (Objects.equals(command, "q"))
                break;
            else if (Objects.equals(command, "p"))
                printTree(this.node);
        }
    }

    @Override
    public void process(WatchedEvent event) {
        if (event.getType() == Event.EventType.NodeCreated) {
            printTree(this.node);
            if (Objects.equals(event.getPath(), this.node))
                this.startProcess();
        } else if (event.getType() == Event.EventType.NodeDeleted) {
            if (Objects.equals(event.getPath(), this.node) && !Objects.isNull(process) && process.isAlive())
                process.destroy();
        }
    }

    private void startProcess() {
        try {
            process = Runtime.getRuntime().exec(this.appName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void printTree(String node) {
        try {
            List<Leaf> descendants = this.getDescendants(node, 0, new ArrayList<>());
            for (Leaf leaf : descendants) {
                for (int i = 0; i < leaf.getDepth(); i++)
                    System.out.print("\t");
                System.out.println(leaf.getNode());
            }
            System.out.println("Number of descendants: " + (descendants.size() - 1));
        } catch (InterruptedException | KeeperException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Leaf> getDescendants(String node, int depth, List<Leaf> tree) throws InterruptedException, KeeperException {
        tree.add(new Leaf(node, depth));
        List<String> children = this.zooKeeper.getChildren(node, this);
        for (String child : children)
            getDescendants(node + "/" + child, depth + 1, tree);
        return tree;
    }

//    private void printNumberOfDescendants(String node) {
//        int numberOfDescendants = 0;
//        try {
//            numberOfDescendants = this.getNumberOfDescendants(node, 0);
//            System.out.println("Number of descendants: " + numberOfDescendants);
//        } catch (InterruptedException | KeeperException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private int getNumberOfDescendants(String node, int depth) throws InterruptedException, KeeperException {
//        for (int i = 0; i < depth; i++)
//            System.out.print("\t");
//        System.out.println(node);
//
//        List<String> children = this.zooKeeper.getChildren(node, this);
//        if (children.isEmpty())
//            return 0;
//        int nodeDescendants = 0;
//        for (String child : children)
//            nodeDescendants += this.getNumberOfDescendants(node + "/" + child, depth + 1);
//        return children.size() + nodeDescendants;
//    }
}
