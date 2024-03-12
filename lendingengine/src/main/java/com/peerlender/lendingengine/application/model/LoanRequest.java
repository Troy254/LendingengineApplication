package com.peerlender.lendingengine.application.model;


import com.peerlender.lendingengine.domain.model.Money;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

  private int amount;
  private int daysToRepay;
  private double interestRate;

}
