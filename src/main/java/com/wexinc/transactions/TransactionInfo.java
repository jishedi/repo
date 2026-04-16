package com.wexinc.transactions;

import java.time.Instant;

/**
 * Projection for {@link Transaction}
 */
public interface TransactionInfo {
    Long getTransactionId();

    String getPurchaseDescription();

    Instant getTransactionDate();

    Double getPurchaseAmount();
}
