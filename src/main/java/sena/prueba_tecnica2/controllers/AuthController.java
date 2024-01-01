package sena.prueba_tecnica2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sena.prueba_tecnica2.services.EmpleadoServiceImpl;

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

        if (empleadoServiceImpl.authenticate(email, password)) {
            String token = "mi_token";
            return ResponseEntity.ok("Login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }


}
