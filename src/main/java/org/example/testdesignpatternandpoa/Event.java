package org.example.testdesignpatternandpoa;

public class Event {
    private final String agentName;
    private final Transaction transaction;

    public Event(String agentName, Transaction transaction) {
        this.agentName = agentName;
        this.transaction = transaction;
    }

    public String getAgentName() {
        return agentName;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public String toString() {
        return String.format("Event{agentName='%s', transaction=%s}", agentName, transaction);
    }
}

