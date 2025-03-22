package dev.dfonline.flint.hypercube;

import java.util.HashMap;
import java.util.Map;

public enum Node {

    NODE_1("node1", "Node 1"),
    NODE_2("node2", "Node 2"),
    NODE_3("node3", "Node 3"),
    NODE_4("node4", "Node 4"),
    NODE_5("node5", "Node 5"),
    NODE_6("node6", "Node 6"),
    NODE_7("node7", "Node 7"),
    EVENT("event", "Event"),
    BETA("beta", "Node Beta"),
    DEV("dev", "Dev"),
    DEV_2("dev2", "Dev 2"),
    DEV_3("dev3", "Dev 3"),
    LOCAL("local", "Local");

    private static final Map<String, Node> ID_MAP = new HashMap<>();
    private static final Map<String, Node> NAME_MAP = new HashMap<>();

    static {
        for (Node node : values()) {
            ID_MAP.put(node.id, node);
            NAME_MAP.put(node.name, node);
        }
    }

    private final String id;
    private final String name;

    Node(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Node fromId(String serverId) {
        return ID_MAP.get(serverId);
    }

    public static Node fromName(String serverName) {
        return NAME_MAP.get(serverName);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
