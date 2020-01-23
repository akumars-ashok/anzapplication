package com.example.anz.main.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.anz.main.dto.AccountsDTO;
import com.example.anz.main.dto.TransactionDTO;
import com.example.anz.main.model.AccountsEntity;
import com.example.anz.main.model.TransactionEntity;
import com.example.anz.main.repository.AccountsRepository;
import com.example.anz.main.repository.TransactionRepository;


@Service
public class AccountService {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public List<AccountsDTO> getAllAccounts(){
		List<AccountsEntity> accountLists=accountsRepository.findAll();
		List<AccountsDTO> accountDTOs=new ArrayList<AccountsDTO>();
		
		for(AccountsEntity accountsEntity: accountLists) {
			AccountsDTO accountsDTO= AccountsDTO.valueOf(accountsEntity);
			accountDTOs.add(accountsDTO);
		}
		
		logger.info("Account details : {}", accountDTOs);
		return accountDTOs;
	}
	
	public List<TransactionDTO> getTransByAccountNumber(int accountNumber){
		
		logger.info("Transaction details request for customer {}", accountNumber);
		List<TransactionEntity> transactionHistorylist=transactionRepository.findByAccountNumber(accountNumber);
		
		List<TransactionDTO> transactionDTOs=new ArrayList<TransactionDTO>();
		
		for(TransactionEntity transactionEntity: transactionHistorylist) {
			TransactionDTO transactionDTO=TransactionDTO.valueOf(transactionEntity);
			transactionDTOs.add(transactionDTO);
		}
		
		logger.info("Transaction details : {}", transactionDTOs);
		return transactionDTOs;
	}

}
