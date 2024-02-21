package com.peerlender.lendingengine.domain.service;

import com.peerlender.lendingengine.application.model.LoanRequest;
import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.model.LoanApplication;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanApplicationAdapter {

  private UserRepository userRepository;

  @Autowired
  public LoanApplicationAdapter(UserRepository userRepository) {
    this.userRepository = userRepository;
  }


  public LoanApplication transform(LoanRequest req, AppUsers borrower) {
    System.out.println("Service for loan requested");
    Optional<AppUsers> appUsersOptional = userRepository.findById(borrower.getUsername());
    if (appUsersOptional.isPresent()) {
      return new LoanApplication(req.getAmount(), appUsersOptional.get(), req.getDaysToRepay(),
          req.getInterestRate());
    } else {
      throw new UserNotFoundException(borrower.getUsername());
    }
  }
}
