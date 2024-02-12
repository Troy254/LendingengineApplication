package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepository;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanService {
    private LoanApplicationRepository loanApplicationRepository;
    private UserRepository userRepository;
    private LoanRepository loanRepository;

    @Autowired
    public LoanService(LoanApplicationRepository loanApplicationRepository,
        UserRepository userRepository, LoanRepository loanRepository) {
      this.loanApplicationRepository = loanApplicationRepository;
      this.userRepository = userRepository;
      this.loanRepository = loanRepository;
    }

    public void acceptLoan(String applicationId, String lenderUsername){
      System.out.println("passed through service");
     AppUsers lender = userRepository.findById(lenderUsername).orElseThrow(
          ()-> new UserNotFoundException(lenderUsername));
          LoanApplication loanApplication = loanApplicationRepository.findById(
          Long.valueOf(applicationId)).orElseThrow(
          ()-> new UserNotFoundException(applicationId));
      loanRepository.save(new Loan(lender,loanApplication));
    }
    public List<Loan> getLoans(){ return loanRepository.findAll();
    }
  }

