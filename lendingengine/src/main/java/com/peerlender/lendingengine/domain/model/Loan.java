package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Objects;
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
  @OneToOne(cascade = CascadeType.ALL)
  private Money loanAmount;
  private double interestRate;
  private LocalDate dateLent;
  private LocalDate dateDue;
  @OneToOne(cascade = CascadeType.ALL)
  private Money amountRepayed;

  public Loan() {
  }

  public Loan(AppUsers lender,LoanApplication loanApplication) {
    this.id = id;
    this.borrower = loanApplication.getBorrower();
    this.lender = lender;
    this.loanAmount = loanApplication.getAmount();
    this.interestRate = loanApplication.getInterestRate();
    this.dateLent = LocalDate.now();
    this.dateDue = LocalDate.now().plusDays(loanApplication.getRepaymentTermInDays());
    this.amountRepayed = Money.ZERO;
  }

  public void repay(final Money money) {
    borrower.withdraw(money);
    lender.topUp(money);
    amountRepayed = amountRepayed.add(money);
  }

  public Money getAmountOwed(){
  return loanAmount.times(1 + interestRate/100d).minus(amountRepayed);
  }

  public long getId() {
    return id;
  }

  public AppUsers getBorrower() {
    return borrower;
  }

  public AppUsers getLender() {
    return lender;
  }

  public Money getAmountRepayed() {
    return amountRepayed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Loan loan = (Loan) o;
    return id == loan.id && Double.compare(interestRate, loan.interestRate) == 0
        && Objects.equals(borrower, loan.borrower) && Objects.equals(lender,
        loan.lender) && Objects.equals(loanAmount, loan.loanAmount)
        && Objects.equals(dateLent, loan.dateLent) && Objects.equals(dateDue,
        loan.dateDue) && Objects.equals(amountRepayed, loan.amountRepayed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, borrower, lender, loanAmount, interestRate, dateLent, dateDue,
        amountRepayed);
  }

  @Override
  public String toString() {
    return "Loan{" +
        "id=" + id +
        ", borrower=" + borrower +
        ", lender=" + lender +
        ", loanAmount=" + loanAmount +
        ", interestRate=" + interestRate +
        ", dateLent=" + dateLent +
        ", dateDue=" + dateDue +
        ", amountRepayed=" + amountRepayed +
        '}';
  }
}
