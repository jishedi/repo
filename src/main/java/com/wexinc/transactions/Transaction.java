package com.wexinc.transactions;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import java.time.Instant;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_gen")
    @SequenceGenerator(name = "transaction_id_gen", sequenceName = "transaction_id_seq")
    @Column(name = "transaction_id", nullable = false)
    @JsonProperty("transactionId")
    private Long transactionId;

    @Size(max = 50)
    @NotNull
    @Column(name = "purchase_description", nullable = false, length = 50)
    @JsonProperty("purchaseDescription")
    private String purchaseDescription;

    @NotNull
    @Column(name = "transaction_date", nullable = false)
    @JsonProperty("transactionDate")
    private Instant transactionDate;

    @NotNull
    @Column(name = "purchase_amount", nullable = false)
    @JsonProperty("purchaseAmount")
    private Double purchaseAmount;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "created_at", nullable = false)
    @Getter
    private Instant createdAt;

    @Column(name = "updated_at")
    @Getter
    private Instant updatedAt;

    public Transaction(Long transactionId, String purchaseDescription,
                       Instant transactionDate, Double purchaseAmount) {
        this.transactionId = transactionId;
        this.purchaseDescription = purchaseDescription;
        this.transactionDate = transactionDate;
        this.purchaseAmount = purchaseAmount;
    }

    // Getters and Setters
    public Long getTransactionId() { return transactionId; }
    public String getPurchaseDescription() { return purchaseDescription; }
    public Instant getTransactionDate() { return transactionDate; }
    public Double getPurchaseAmount() { return purchaseAmount; }
}
