package sena.prueba_tecnica2.controllers;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sena.prueba_tecnica2.services.EmpleadoServiceImpl;
import io.jsonwebtoken.Jwts;
import sena.prueba_tecnica2.services.TokenService;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private EmpleadoServiceImpl empleadoServiceImpl;


    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody Map<String, String> credentials ) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        String rol = empleadoServiceImpl.getRoleByEmail(email);

        if (empleadoServiceImpl.authenticate(email, password)) {
            String token = generateToken(email, rol);
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    // Método para generar el token JWT
    private String generateToken(String email, String rol) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("email", email);
        extraClaims.put("rol", rol);

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date(issuedAt.getTime() + (TokenService.EXPIRATION_IN_MINUTES * 60 * 1000));

        SecretKey secretKey = Keys.hmacShaKeyFor(TokenService.SECRET_KEY.getBytes());

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expiration)
                .setIssuedAt(issuedAt)
                .addClaims(extraClaims)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }


}
