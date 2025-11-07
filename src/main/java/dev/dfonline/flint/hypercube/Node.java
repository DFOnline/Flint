package dev.dfonline.flint.hypercube;

import java.util.HashMap;
import java.util.Map;

public enum Node {

    NODE_1("node1", "Node 1", false),
    NODE_2("node2", "Node 2", false),
    NODE_3("node3", "Node 3", false),
    NODE_4("node4", "Node 4", false),
    NODE_5("node5", "Node 5", false),
    NODE_6("node6", "Node 6", false),
    NODE_7("node7", "Node 7", false),
    EVENT("event", "Event", false),
    BETA("beta", "Node Beta", true),
    DEV("dev", "Dev", true),
    DEV_2("dev2", "Dev 2", true),
    DEV_3("dev3", "Dev 3", true),
    DEV_4("dev4", "Dev 4", true),
    LOCAL("local", "Local", true),
    PRIVATE("private", "Private Node", false),
    ALPHA_1("alpha1", "Alpha 1", true),
    ALPHA_2("alpha2", "Alpha 2", true);

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
    private final boolean isActionDumpObtainable;

    Node(String id, String name, boolean isActionDumpObtainable) {
        this.id = id;
        this.name = name;
        this.isActionDumpObtainable = isActionDumpObtainable;
    }

    public static Node fromId(String serverId) {
        return ID_MAP.get(serverId);
    }

    public static Node fromName(String serverName) {
        if(serverName.startsWith("Private Node")) return PRIVATE;

        return NAME_MAP.get(serverName);
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public boolean isActionDumpObtainable() {
        return this.isActionDumpObtainable;
    }

}
