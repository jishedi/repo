package com.wexinc.transactions;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;

@Component
public class TransactionManager extends JpaTransactionManager {
}
