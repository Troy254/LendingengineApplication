package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Currency;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.model.Money;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.repository.LoanRepository;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
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
  @Transactional
  public void acceptLoan(String applicationId, String lenderUsername) {
    System.out.println("passed through service");
    AppUsers lender = findAppUsers(lenderUsername);
    LoanApplication loanApplication = findLoanApplication(applicationId);
    AppUsers borrower = loanApplication.getBorrower();
    Money money = new Money(loanApplication.getAmount(), Currency.USD);
    lender.withdraw(money);
    borrower.topUp(money);
    loanRepository.save(new Loan(lender, loanApplication));
  }

  public List<Loan>  findAllBorrowedLoans(final AppUsers borrower) {
    return loanRepository.findAllByBorrower(borrower);
  }

  public List<Loan> findAllLentLoans(final AppUsers lender) {
    return loanRepository.findAllByLender(lender);
  }

  private LoanApplication findLoanApplication(String applicationId) {
    return loanApplicationRepository.findById(
        Long.valueOf(applicationId)).orElseThrow(
        () -> new UserNotFoundException(applicationId));
  }

  private LoanApplication findLoanApplication1(String applicationId) {
    return loanApplicationRepository.findById(
        Long.valueOf(applicationId)).orElseThrow(()-> new UserNotFoundException(applicationId));
  }
  private AppUsers findAppUsers(String lenderUsername) {
    return userRepository.findById(lenderUsername).orElseThrow(
        () -> new UserNotFoundException(lenderUsername));
  }

  public List<Loan> getLoans() {

    return loanRepository.findAll();
  }
}

