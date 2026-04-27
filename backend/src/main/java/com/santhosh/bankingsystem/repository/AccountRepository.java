package com.santhosh.bankingsystem.repository;

import com.santhosh.bankingsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
