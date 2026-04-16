package com.wexinc.transactions.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.wexinc.transactions.Transaction;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/transactions")
    public String getTransactions(@RequestParam(value = "days", defaultValue = "30") String days) throws JsonProcessingException {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction1 = new Transaction(1001L, "Walmart-1",
                Instant.parse("2026-04-02T10:15:30Z"), 2172.34D);
        Transaction transaction2 = new Transaction(1002L, "Walmart-2",
                Instant.parse("2026-04-01T10:15:30Z"), 161.45D);
        Transaction transaction3 = new Transaction(1003L, "Walmart-3",
                Instant.parse("2026-03-31T10:15:30Z"), 35623.87D);
        Transaction transaction4 = new Transaction(1004L, "Walmart",
                Instant.parse("2026-03-15T10:15:30Z"), 94.99D);
        transactionList.add(transaction1);
        transactionList.add(transaction2);
        transactionList.add(transaction3);
        transactionList.add(transaction4);

        return createJson(transactionList);
    }

    private String createJson(List<Transaction> transactionList) throws JsonProcessingException {
        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Manual registration
        objectMapper.registerModule(new JavaTimeModule());

        // Optional: Disable writing as numeric timestamps to get ISO-8601 strings
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // Convert the transaction list object directly to JSON string with pretty print
        String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(transactionList);

        return jsonString;
    }
}
