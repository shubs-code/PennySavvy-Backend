package com.project.spring.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.spring.datajpa.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    List<Transaction> findAllByEmail(String email);
}
