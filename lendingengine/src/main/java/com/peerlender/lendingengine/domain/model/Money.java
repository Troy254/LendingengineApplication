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

  public static final Money ZERO = new Money(0, Currency.USD);
  @Id
  @GeneratedValue
  private long id;
  private Currency currency;
  private double amount;

  public Money(double amount, Currency currency) {
    this.currency = currency;
    this.amount = amount;
  }

  public Money times(double multiplier){
    return new Money(amount * multiplier, currency);
  }

  public Money add(final Money money) {
    if(currency!= money.getCurrency()){
    throw new IllegalArgumentException();
  }
    return new Money(amount + money.getAmount(),currency);
}

public Money minus(final Money money) {
    if (currency != money.getCurrency() || amount < money.getAmount()) {
      throw new IllegalArgumentException();
    }
    return new Money(amount - money.getAmount(), currency);
  }
}
