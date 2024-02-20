package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUsers {

  @Id
  private String username;
  private String firstName;
  private String lastName;
  private int age;
  private String occupation;
  @OneToOne(cascade = CascadeType.ALL)
  private Balance balance;


  public void topUp(Money money) {
    balance.topUp(money);
  }

  public void withdraw(Money money) {
    balance.withdraw(money);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    AppUsers appUsers = (AppUsers) o;
    return age == appUsers.age && Objects.equals(username, appUsers.username)
        && Objects.equals(firstName, appUsers.firstName) && Objects.equals(
        lastName, appUsers.lastName) && Objects.equals(occupation, appUsers.occupation)
        && Objects.equals(balance, appUsers.balance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, firstName, lastName, age, occupation, balance);
  }
}
