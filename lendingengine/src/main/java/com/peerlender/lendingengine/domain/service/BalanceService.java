package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.Money;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BalanceService {
  private UserRepository userRepository;

  @Autowired
  public BalanceService(UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Transactional
  public void topUpBalance(Money money, String authToken){
    AppUsers appUsers = findUser(authToken);
    appUsers.topUp(money);
  }

  @Transactional
  public void withdrawFromBalance(Money money, String authToken){
    AppUsers appUsers = findUser(authToken);
    appUsers.withdraw(money);
  }

  private AppUsers findUser(String authToken){
    return userRepository.findById(authToken)
        .orElseThrow(()-> new UserNotFoundException(authToken));
  }
}
