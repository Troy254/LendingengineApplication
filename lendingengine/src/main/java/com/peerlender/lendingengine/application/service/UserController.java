package com.peerlender.lendingengine.application.service;

import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final TokenValidationService tokenValidationService;

  private final UserRepository userRepository;

@Autowired
  public UserController(TokenValidationService tokenValidationService,
      UserRepository userRepository) {
    this.tokenValidationService = tokenValidationService;
    this.userRepository = userRepository;
  }

  //find all Users
  @GetMapping(value = "/users")
  public List<AppUsers> findUsers(HttpServletRequest request) {
  tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
  tokenValidationService.validateTokenAndGetUser(request.getHeader(HttpHeaders.AUTHORIZATION));
  return userRepository.findAll();
  }
}
