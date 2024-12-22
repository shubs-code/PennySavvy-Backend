package com.project.spring.datajpa.controller;
import org.springframework.web.bind.annotation.RestController;

import com.project.spring.datajpa.model.Transaction;
import com.project.spring.datajpa.model.User;
import com.project.spring.datajpa.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransanctions( @RequestParam(required = false) String email) {
		try {
			List<Transaction> transaction = new ArrayList<Transaction>();
            
            if(email != null && !email.isBlank()){
                transaction = transactionRepository.findAllByEmail(email);
            }else transaction = transactionRepository.findAll();

            
			return new ResponseEntity<>(transaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping("/transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
		try {
            Transaction savedTransaction = transactionRepository.save(transaction);
			return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction, @PathVariable Long id) {
		try {
            Transaction oldTransaction = transactionRepository.findById(id).get();
            oldTransaction.setEmail(transaction.getEmail());
            oldTransaction.setAmount(transaction.getAmount());
            oldTransaction.setDate(transaction.getDate());
            oldTransaction.setDescription(transaction.getDescription());
            oldTransaction.setType(transaction.getType());
            Transaction savedTransaction = transactionRepository.save(oldTransaction);
			return new ResponseEntity<>(savedTransaction, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<HashMap<String, String>> deleteTransaction(@PathVariable Long id) {
		try {
            transactionRepository.deleteById(id);
            HashMap<String, String> response = new HashMap<>();
            response.put("status","200");
            response.put("message","deleted");
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
