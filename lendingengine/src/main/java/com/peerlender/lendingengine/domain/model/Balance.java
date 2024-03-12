package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MapKeyClass;
import jakarta.persistence.OneToMany;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Balance {

  @Id
  @GeneratedValue
  private long id;
  @ElementCollection
  @MapKeyClass(Currency.class)
  @OneToMany(targetEntity = Money.class,cascade = CascadeType.ALL)
  private Map<Currency,Money> moneyMap = new HashMap<>();

  public void topUp(Money money) {
    if(moneyMap.get(money.getCurrency()) == null) {
      moneyMap.put(money.getCurrency(), money);
    } else {
      moneyMap.put(money.getCurrency(), moneyMap.get(money.getCurrency()).add(money));
    }
  }

  public void withdraw(Money money) {
    Money moneyInBalance = moneyMap.get(money.getCurrency());
    if(moneyInBalance == null) {
      throw new IllegalStateException();
    } else {
      moneyMap.put(money.getCurrency(), moneyMap.get(money.getCurrency()).minus(money));
    }
  }




}
