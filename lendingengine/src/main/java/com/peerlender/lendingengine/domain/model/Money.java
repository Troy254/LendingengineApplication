package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Money {
  @Id
  @GeneratedValue
  private long id;
  private Currency currency;
  private double amount;

  public Money(double amount, Currency currency) {
    this.currency = currency;
    this.amount = amount;
  }

  public Money add(Money money) {
    if (currency != money.getCurrency()) {
      throw new IllegalArgumentException();
    }
    return new Money(amount + money.getAmount(), currency);
  }

  public Money minus(Money money) {
    if (currency != money.getCurrency() || amount < money.getAmount()) {
      throw new IllegalArgumentException();
    }
    return new Money(amount - money.getAmount(), currency);
  }
}
