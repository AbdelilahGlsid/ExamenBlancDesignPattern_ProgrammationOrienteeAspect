package org.example.testdesignpatternandpoa;

public class DefaultStrategy implements NotificationStrategy {
    @Override
    public void handleNotification(Event event) {
        System.out.println("Default handling for event: " + event);
    }
}
