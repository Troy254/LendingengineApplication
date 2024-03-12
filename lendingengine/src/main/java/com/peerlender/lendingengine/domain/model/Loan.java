package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
public class Loan {

  @Id
  @GeneratedValue
  private long id;
  @ManyToOne
  private AppUsers borrower;
  @ManyToOne
  private AppUsers lender;

  private int amount;
  private double interestRate;
  private LocalDate dateLent;
  private LocalDate dateDue;

  public Loan() {
  }

  public Loan(AppUsers lender,LoanApplication loanApplication) {
    this.id = id;
    this.borrower = loanApplication.getBorrower();
    this.lender = lender;
    this.amount = loanApplication.getAmount();
    this.interestRate = loanApplication.getInterestRate();
    this.dateLent = LocalDate.now();
    this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentTermInDays());
  }

}
