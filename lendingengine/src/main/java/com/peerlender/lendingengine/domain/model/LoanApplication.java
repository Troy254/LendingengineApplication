package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Objects;


@Entity
public class LoanApplication {

  @Id
  @GeneratedValue
  private int amount;

  @ManyToOne
  private AppUsers borrower;
  private int repaymentTermInDays;
  private double interestRate;

  public LoanApplication(int amount, AppUsers borrower, int repaymentTermInDays,
      double interestRate) {
    this.amount = amount;
    this.borrower = borrower;
    this.repaymentTermInDays = repaymentTermInDays;
    this.interestRate = interestRate;
  }

  public LoanApplication() {

  }

  public Money getAmount() {
    return new Money(amount,Currency.USD);
  }

  public AppUsers getBorrower() {
    return borrower;
  }

  public int getRepaymentTermInDays() {
    return repaymentTermInDays;
  }

  public double getInterestRate() {
    return interestRate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    LoanApplication that = (LoanApplication) o;
    return amount == that.amount && repaymentTermInDays == that.repaymentTermInDays
        && Double.compare(interestRate, that.interestRate) == 0 && Objects.equals(
        borrower, that.borrower);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, borrower, repaymentTermInDays, interestRate);
  }

  @Override
  public String toString() {
    return "LoanApplication{" +
        "amount=" + amount +
        ", borrower=" + borrower +
        ", repaymentTermInDays=" + repaymentTermInDays +
        ", interestRate=" + interestRate +
        '}';
  }
}