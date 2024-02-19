package com.peerlender.lendingengine.domain.model;

import jakarta.persistence.Entity;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Balance {

  private Map<Currency,Money> moneyMap = new HashMap<>();

}
