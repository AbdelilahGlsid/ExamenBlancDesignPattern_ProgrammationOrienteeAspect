package org.example.testdesignpatternandpoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class TestDesignPatternAndPoaApplication {

    public static void main(String[] args) {
        //SpringApplication.run(TestDesignPatternAndPoaApplication.class, args);

        // --- TEST: Création de Transaction via Builder ---
        System.out.println("---- TEST: Création de Transaction ----");

        // Créer une transaction
        Transaction transaction1 = new Transaction.Builder()
                .setId("TXN123")
                .setDate(new Date())
                .setMontant(1000.0)
                .setType("Vente")
                .build();

        System.out.println("Transaction 1:");
        System.out.println("ID: " + transaction1.getId());
        System.out.println("Date: " + transaction1.getDate());
        System.out.println("Montant: " + transaction1.getMontant());
        System.out.println("Type: " + transaction1.getType());

        // Tester une transaction avec un type incorrect
        try {
            Transaction invalidTransaction = new Transaction.Builder()
                    .setId("TXN124")
                    .setDate(new Date())
                    .setMontant(500.0)
                    .setType("Invalid")
                    .build();
        } catch (IllegalArgumentException e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        System.out.println("---- TEST: Création d'Agent et ajout de transactions ----");

        // Créer des agents
        Agent agent1 = new Agent("Agent1");
        Agent agent2 = new Agent("Agent2");

        // Abonner agent2 à agent1
        agent1.subscribe(agent2);

        // Créer des transactions
        Transaction transaction2 = new Transaction.Builder()
                .setId("TXN1")
                .setDate(new Date())
                .setMontant(500)
                .setType("Achat")
                .build();

        Transaction transaction3 = new Transaction.Builder()
                .setId("TXN2")
                .setDate(new Date())
                .setMontant(1000)
                .setType("Vente")
                .build();

        // Ajouter des transactions à agent1
        System.out.println("Ajout de transactions à Agent1:");
        agent1.addTransaction(transaction2);
        agent1.addTransaction(transaction3);

        // Vérifier les transactions de agent2 (qui devrait avoir été notifié)
        System.out.println("Transactions de Agent2 après notification:");
        agent2.getTransactions().forEach(transaction -> System.out.println(transaction.getId() + " - " + transaction.getMontant()));

        // Vérifier la transaction avec le plus grand montant chez Agent1
        Transaction maxTransaction = agent1.getMaxTransaction();
        System.out.println("Transaction avec le plus grand montant chez Agent1: " + maxTransaction.getId() + " - " + maxTransaction.getMontant());

        // --- TEST: Sécurité avec @SecuredBy ---
        System.out.println("---- TEST: Sécurité avec @SecuredBy ----");

        // Créer un utilisateur simulé (par exemple)
        UserContext.setRoles("role1");

        // Initialiser Spring context (en supposant que vous utilisez Spring pour gérer les aspects)
        // ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Créer une instance simulée d'un service nécessitant des rôles (p. ex., AgentService)
        // AgentService agentService = context.getBean(AgentService.class);

        try {
            // Simuler l'appel d'une méthode sécurisée sans rôle correct
            // agentService.securedMethod();
            System.out.println("Tentative d'accès sans rôle correct (Devrait échouer)...");

            // Tester la méthode sécurisée avec un rôle suffisant
            UserContext.setRoles("role1,role2");  // Ajouter un rôle suffisant pour l'accès

            // agentService.securedMethod();
            System.out.println("Accès autorisé après avoir ajouté les bons rôles !");
        } catch (SecurityException e) {
            System.out.println("Access denied: " + e.getMessage());
        }
    }

}
