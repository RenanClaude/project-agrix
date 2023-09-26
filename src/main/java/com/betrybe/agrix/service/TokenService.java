package com.betrybe.agrix.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.betrybe.agrix.model.entities.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Token Service.
 */
@Service
public class TokenService {

  private final Algorithm algorithm;

  public TokenService(@Value("${api.security.token.secret}") String secret) {
    this.algorithm = Algorithm.HMAC256(secret);
  }

  /**
   * Method to generate a token.
   */
  public String generateToken(Person person) {

    return JWT.create()
        .withIssuer("Agrix")
        .withSubject(person.getUsername())
        .withExpiresAt(generateExpirationDate())
        .sign(algorithm);
  }

  /**
   * Method to validate a Token.
   */
  public String validateToken(String token) {
    return JWT.require(this.algorithm)
        .withIssuer("Agrix")
        .build()
        .verify(token)
        .getSubject();
  }

  private Instant generateExpirationDate() {
    return LocalDateTime.now()
        .plusHours(2)
        .toInstant(ZoneOffset.of("-03:00"));
  }

}
