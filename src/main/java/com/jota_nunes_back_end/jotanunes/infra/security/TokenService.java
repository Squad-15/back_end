//package com.jota_nunes_back_end.jotanunes.infra.security;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTCreationException;
//import com.auth0.jwt.exceptions.JWTVerificationException;
//import com.jota_nunes_back_end.jotanunes.models.UserAccount;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneOffset;
//
//@Service
//public class TokenService {
//    @Value("${api.security.token.secret}")
//    private String secret;
//
//    public String generateToken(UserAccount userAccount){
//        try{
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            String token = JWT.create()
//                    .withIssuer("auth-api")
//                    .withSubject(userAccount.getNumberRegister())
//                    .withExpiresAt(genExpirationDate())
//                    .sign(algorithm);
//            return token;
//        } catch (JWTCreationException exception) {
//            throw new RuntimeException("Error while generating token", exception);
//        }
//    }
//
//    public String validateToken(String token){
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            return JWT.require(algorithm)
//                    .withIssuer("auth-api")
//                    .build()
//                    .verify(token)
//                    .getSubject();
//        } catch (JWTVerificationException exception){
//            return "";
//        }
//    }
//
//    private Instant genExpirationDate(){
//        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
//    }
//}

package com.jota_nunes_back_end.jotanunes.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(userAccount.getNumberRegister())
                    .withClaim("id", userAccount.getId())
                    .withClaim("roleUser", userAccount.getRoleUser().name())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}

