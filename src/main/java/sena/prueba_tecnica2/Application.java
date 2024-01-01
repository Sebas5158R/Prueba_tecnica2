package sena.prueba_tecnica2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

	public static final int EXPIRATION_IN_MINUTES = 30;
	public static final String SECRET_KEY = "MI CLAVE ES SEGURA 123456789 ABC abc";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		Map<String, Object> extraClaims = buildExtractClaims();

		String jwt = buildJws(extraClaims);

		System.out.println(jwt);

		try {
			Claims payload = verifyJws(jwt);
			System.out.println(payload.getSubject());
		}catch (JwtException e) {
			System.out.println(e.getMessage());
		}
	}

	private static Claims verifyJws(String jwt) {
		return Jwts.parser()
				.verifyWith(generateKey())
				.build()
				.parseSignedClaims(jwt)
				.getPayload();
	}

	private static String buildJws(Map<String, Object> extraClaims) {
		Date issuedAt =  new Date(System.currentTimeMillis());
		Date expiration =  new Date( issuedAt.getTime() + (EXPIRATION_IN_MINUTES * 60 * 1000) );

		String jwt = Jwts.builder()

				.header()
				.type("JWT")
				.and()

				.subject("springboot")
				.expiration(expiration)
				.issuedAt(issuedAt)
				.claims(extraClaims)

				.signWith(generateKey(), Jwts.SIG.HS256)

				.compact();
		return jwt;
	}

	private static Map<String, Object> buildExtractClaims() {
		Map<String, Object> extraClaims = new HashMap<>();
		extraClaims.put("name", "springboot");
		return extraClaims;
	}

	public static SecretKey generateKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	}

}
