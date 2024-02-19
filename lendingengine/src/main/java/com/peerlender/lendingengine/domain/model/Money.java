package com.peerlender.lendingengine.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money {

  private Currency currency;
  private double amount;

  public Money(double amount,Currency currency) {
    this.currency = currency;
    this.amount = amount;
  }

  public Money add(Money money){
    if(currency != money.getCurrency()) {
      throw new IllegalArgumentException();
    }
    return new Money(amount + money.getAmount(),currency);
    }

  public Money minus(Money money){
    if(currency != money.getCurrency() || amount < money.getAmount()){
      throw new IllegalArgumentException();
    }
    return new Money(amount - money.getAmount(),currency);
  }
  }
