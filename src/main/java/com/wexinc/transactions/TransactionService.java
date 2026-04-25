package com.wexinc.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionManager transactionManager;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionInfo> getTransactions(Instant instant) {
        return transactionRepository.getTransactionsByTransactionDateAfter(instant);
    }

    public TransactionInfo getTransactionById(Long id) {
        return transactionRepository.findTransactionByTransactionIdIs(id)
                        .orElseThrow(()-> new TransactionNotFoundException("Transaction not found"));
    }

    @Transactional("transactionManager")
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
