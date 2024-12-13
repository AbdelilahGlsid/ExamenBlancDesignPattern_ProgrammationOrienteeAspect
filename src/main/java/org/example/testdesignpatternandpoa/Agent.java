package org.example.testdesignpatternandpoa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agent {
    private final String name;
    private final List<Transaction> transactions = new ArrayList<>();
    private final List<Agent> observers = new ArrayList<>();
    private NotificationStrategy strategy = new DefaultStrategy();

    public Agent(String name) {
        this.name = name;
    }

    public void subscribe(Agent observer) {
        observers.add(observer);
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        notifyObservers(transaction);
    }

    private void notifyObservers(Transaction transaction) {
        Event event = new Event(this.name, transaction);
        for (Agent observer : observers) {
            observer.update(event);
        }
    }

    public void update(Event event) {
        strategy.handleNotification(event);
    }

    public void setStrategy(NotificationStrategy strategy) {
        this.strategy = strategy;
    }

    public String getName() {
        return name;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getMaxTransaction() {
        return transactions.stream().max(Comparator.comparing(Transaction::getMontant)).orElse(null);
    }
}

