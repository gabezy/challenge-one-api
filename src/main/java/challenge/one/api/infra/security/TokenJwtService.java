package challenge.one.api.infra.security;

import challenge.one.api.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenJwtService {

    @Value("${api.security.token.secret}")
    private String secret;
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.create()
                    .withIssuer("challengeOne")
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(dateExpires())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException("Error generate token");
        }
    }

    public String getSubject(String jwtToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
           return JWT.require(algorithm)
                    .withIssuer("challengeOne")
                    .build()
                   .verify(jwtToken)
                   .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Error generate token");
        }
    }

    private Instant dateExpires() {
       return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
