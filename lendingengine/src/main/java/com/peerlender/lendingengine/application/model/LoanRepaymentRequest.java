package com.peerlender.lendingengine.application.model;

import com.peerlender.lendingengine.domain.model.Currency;
import com.peerlender.lendingengine.domain.model.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRepaymentRequest {

  private double amount;
  private long loanId;


  public Money getAmount(){
    return new Money(amount, Currency.USD);
  }
}
