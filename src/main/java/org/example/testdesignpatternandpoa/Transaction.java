package org.example.testdesignpatternandpoa;

import java.util.Date;

public class Transaction {
    private final String id;
    private final Date date;
    private final double montant;
    private final String type;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.montant = builder.montant;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public double getMontant() {
        return montant;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Transaction{id='%s', date=%s, montant=%.2f, type='%s'}",
                id, date, montant, type);
    }

    public static class Builder {
        private String id;
        private Date date;
        private double montant;
        private String type;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setMontant(double montant) {
            this.montant = montant;
            return this;
        }

        public Builder setType(String type) {
            if (!type.equalsIgnoreCase("Vente") && !type.equalsIgnoreCase("Achat")) {
                throw new IllegalArgumentException("Type must be 'Vente' or 'Achat'");
            }
            this.type = type;
            return this;
        }

        public Transaction build() {
            if (id == null || date == null || type == null) {
                throw new IllegalStateException("Missing required fields for Transaction");
            }
            return new Transaction(this);
        }
    }
}
