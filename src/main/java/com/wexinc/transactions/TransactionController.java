package com.wexinc.transactions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public String getTransactions(@RequestParam(value = "days", defaultValue = "30") String days) throws JsonProcessingException {
        Instant now = Instant.now();
        Instant daysAgo = now.minus(Integer.parseInt(days), ChronoUnit.DAYS);

        List<TransactionInfo> transactionList = transactionService.getTransactions(daysAgo);

        return createJson(transactionList);
    }

    @GetMapping("/{id}")
    ResponseEntity<TransactionInfo> getTransactionById(@PathVariable Long id) {
        var transaction = transactionService.getTransactionById(id);
        return ResponseEntity.ok(transaction);
    }

    record CreateTransactionPayload(
            @NotEmpty(message = "Purchase Description is required")
            String purchaseDescription,
            @NotNull(message = "Transaction Date is required")
            Instant transactionDate,
            @NotNull(message = "Purchase Amount is required")
            Double purchaseAmount) {}

    @PostMapping
    ResponseEntity<Void> createTransaction(
            @Valid @RequestBody CreateTransactionPayload payload) {
        var transaction = new Transaction();
        transaction.setPurchaseDescription(payload.purchaseDescription());
        transaction.setTransactionDate(payload.transactionDate());
        transaction.setPurchaseAmount(payload.purchaseAmount());
        transaction.setCreatedAt(Instant.now());
        var savedTransaction = transactionService.createTransaction(transaction);
        var url = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .build(savedTransaction.getTransactionId());
        return ResponseEntity.created(url).build();
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    ResponseEntity<Void> handle(TransactionNotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    private String createJson(List<TransactionInfo> transactionList) throws JsonProcessingException {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Manual registration
        objectMapper.registerModule(new JavaTimeModule());

        // Optional: Disable writing as numeric timestamps to get ISO-8601 strings
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Convert the transaction list object directly to JSON string with pretty print
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionList);
    }
}
