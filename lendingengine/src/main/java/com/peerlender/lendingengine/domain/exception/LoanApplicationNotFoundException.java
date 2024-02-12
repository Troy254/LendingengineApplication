package com.peerlender.lendingengine.domain.exception;

public class LoanApplicationNotFoundException extends RuntimeException {

  public LoanApplicationNotFoundException(long loanApplicationId) {

  super("Loan application with Id: " + loanApplicationId + "was not found");

  }
}
