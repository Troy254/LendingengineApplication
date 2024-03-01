package com.peerlender.lendingengine.application;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.application.service.TokenValidationService;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Loan;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.LoanApplicationRepository;
import com.peerlender.lendingengine.domain.service.LoanApplicationAdapter;
import com.peerlender.lendingengine.domain.service.LoanService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

  private LoanApplicationRepository loanApplicationRepository;

  private LoanApplicationAdapter loanApplicationAdapter;

  private LoanService loanService;

  private TokenValidationService tokenValidationService;

  @Autowired
  public LoanController(LoanApplicationRepository loanApplicationRepository,
      LoanApplicationAdapter loanApplicationAdapter, LoanService loanService,
      TokenValidationService tokenValidationService) {
    this.loanApplicationRepository = loanApplicationRepository;
    this.loanApplicationAdapter = loanApplicationAdapter;
    this.loanService = loanService;
    this.tokenValidationService = tokenValidationService;
  }

  //Request a Loan
  @PostMapping(value = "/loan/request")
  public void requestLoan(@RequestBody LoanRequest loanRequest, HttpServletRequest request) {
    AppUsers borrower = tokenValidationService.validateTokenAndGetUser(
        request.getHeader(HttpHeaders.AUTHORIZATION));
    LoanApplication loanApplication = loanApplicationAdapter.transform(loanRequest, borrower);
    loanApplicationRepository.save(loanApplication);
  }

  //find all loan requests
  @GetMapping(value = "/loan/requests")
  public List<LoanApplication> findAllLoanApplications(HttpServletRequest request) {
    tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
    return loanApplicationRepository.findAll();
  }

  @GetMapping(value = "/loan/borrowed")
  public List<Loan> findBorrowedLoans(@RequestHeader String authorization) {
  AppUsers borrower = tokenValidationService.validateTokenAndGetUser(authorization);
  return loanService.findAllBorrowedLoans(borrower);
  }

  @GetMapping(value = "/loan/lent")
  public List<Loan> findLentLoans(@RequestHeader String authorization) {
    AppUsers lender = tokenValidationService.validateTokenAndGetUser(authorization);
    return loanService.findAllLentLoans(lender);
  }

  //Accept A Loan
  @PostMapping(value = "/loan/accept/{loanApplicationId}")
  public void acceptLoan(@PathVariable String loanApplicationId,
      HttpServletRequest request) {
    AppUsers lender = tokenValidationService.validateTokenAndGetUser(
        request.getHeader(HttpHeaders.AUTHORIZATION));
    loanService.acceptLoan(loanApplicationId, lender.getUsername());
  }

  @PostMapping(value = "/loan/repay")
  public void repayLoan(){

  }

  //Getting All The Loans
  @GetMapping(value = "/loans")
  public List<Loan> getLoans(HttpServletRequest request) {
    tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
    return loanService.getLoans();
  }
}
