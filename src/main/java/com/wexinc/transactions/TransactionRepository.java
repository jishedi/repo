package com.wexinc.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<TransactionInfo> getTransactionsByTransactionDateAfter(Instant instant);

    Optional<TransactionInfo> findTransactionByTransactionIdIs(Long transactionId);

    @Override
    <S extends Transaction> S save(S entity);
}