package lkosaka.gnacoesunidas.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lkosaka.gnacoesunidas.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String buildToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(
                        LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"))
                    )
                    .sign(algorithm);
        } catch (JWTCreationException jwtCreationException) {
            throw new RuntimeException("Error generate token JWT", jwtCreationException);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("API")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("JWT expired", e);
        }
    }
}
