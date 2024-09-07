package com.poly.datn_n10.dao;

import com.poly.datn_n10.enity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
    // Custom query methods (if needed) can be defined here
    Transaction findByTransactionHash(String transactionHash);
}
