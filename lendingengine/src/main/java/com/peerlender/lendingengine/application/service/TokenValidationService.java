package com.peerlender.lendingengine.application.service;

import com.peerlender.lendingengine.domain.exception.UserNotFoundException;
import com.peerlender.lendingengine.domain.model.AppUsers;
import com.peerlender.lendingengine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class TokenValidationService {
  private final UserRepository userRepository;
  private final RestTemplate restTemplate = new RestTemplate();
  private final String securityContextBaseUrl;

  @Autowired
  public TokenValidationService(final UserRepository userRepository,
      @Value("http://localhost:8081") final String securityContextBaseUrl) {
    this.userRepository = userRepository;
    this.securityContextBaseUrl = securityContextBaseUrl;
  }
  public AppUsers validateTokenAndGetUser(String token) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.AUTHORIZATION, token);
    HttpEntity httpEntity = new HttpEntity(httpHeaders);

    //Using restTemplate to make a POST request to the "/user/validate" endpoint
    ResponseEntity<String> response = restTemplate.exchange(securityContextBaseUrl + "/user/validate",
        HttpMethod.POST, httpEntity, String.class);

    //Checking if the response status is OK (HTTP 200)
    if (response.getStatusCode().equals(HttpStatus.OK)) {
      // Retrieving user by ID from userRepository using the response body
      return userRepository.findById(response.getBody())
          .orElseThrow(() -> new UserNotFoundException(response.getBody()));
    }
    //Throwing a RuntimeException if the response status is not OK
    throw new RuntimeException("Token Invalid");

  }
}