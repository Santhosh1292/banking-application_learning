package com.santhosh.bankingsystem.repository;

import com.santhosh.bankingsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}