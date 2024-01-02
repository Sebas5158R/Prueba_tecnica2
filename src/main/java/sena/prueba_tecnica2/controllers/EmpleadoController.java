package sena.prueba_tecnica2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sena.prueba_tecnica2.models.Empleado;
import sena.prueba_tecnica2.services.EmpleadoServiceImpl;
import sena.prueba_tecnica2.services.TokenService;

import java.util.List;


@RestController("/empleado")
@RequestMapping(value = "/empleado")
@CrossOrigin(origins = "http://localhost:3000")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceImpl empleadoServiceImpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/addPrimerEmpleado")
    public String addPrimerEmpleado(@RequestBody Empleado empleado) {
        return empleadoServiceImpl.addEmpleado(empleado);
    }

    @GetMapping(path = { "/listarEmpleados", "empleados" })
    public ResponseEntity<List<Empleado>> listarEmpleados(@RequestHeader("Authorization") String token) {
        String rol = tokenService.extractRolFromToken(token);

        if ("super administrador".equals(rol) || "administrador".equals(rol)) {
            List<Empleado> empleados = empleadoServiceImpl.getAllEmpleados();
            return ResponseEntity.ok(empleados);
        } else {
            String msg = "No tienes permisos para acceder a esta función";
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    @PostMapping("/add")
    public String addEmpleado(@RequestBody Empleado empleado, @RequestHeader("Authorization") String token) {
        String email = tokenService.extractEmailFromToken(token);
        String rol = tokenService.extractRolFromToken(token);

        if ("super administrador".equals(rol) || "administrador".equals(rol)) {
            return empleadoServiceImpl.addEmpleado(empleado);

        }else {
            return "Acceso denegado no tienes los permisos necesarios";
        }
    }

    @PutMapping("/update/{idEmpleado}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer idEmpleado, @RequestBody Empleado empleado) {

        Empleado empleadoExistente = empleadoServiceImpl.getEmpleadoById(idEmpleado);

        if (empleado.getPassword() == null || empleado.getPassword().isEmpty()) {
            empleado.setPassword(empleadoExistente.getPassword());
        } else {
            String contraseñaCodificada = passwordEncoder.encode(empleado.getPassword());
            empleado.setPassword(contraseñaCodificada);
        }

        Empleado empleadoActualizado = empleadoServiceImpl.updateEmpleado(idEmpleado, empleado);


        if (empleadoActualizado != null) {
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
