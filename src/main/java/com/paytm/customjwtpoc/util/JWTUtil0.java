package com.paytm.customjwtpoc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil0 {
  public static String getPermission(String token) {
    Algorithm algorithm = Algorithm.HMAC256("secret");
    JWTVerifier verifier = JWT.require(algorithm)
        .build();

    DecodedJWT jwt = verifier.verify(token);

    String subject = jwt.getSubject();
    return subject;
  }
}
