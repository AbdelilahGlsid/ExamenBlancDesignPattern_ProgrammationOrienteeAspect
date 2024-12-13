package org.example.testdesignpatternandpoa;

import java.util.HashMap;
import java.util.Map;

public class Container {
    private static final Container instance = new Container();
    private final Map<String, Agent> agents = new HashMap<>();

    private Container() {}

    public static Container getInstance() {
        return instance;
    }

    public void addAgent(Agent agent) {
        agents.put(agent.getName(), agent);
    }

    public void removeAgent(String name) {
        agents.remove(name);
    }

    public Agent getAgent(String name) {
        return agents.get(name);
    }

    public void display() {
        agents.values().forEach(agent -> System.out.println(agent.getName()));
    }
}
