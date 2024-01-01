package sena.prueba_tecnica2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sena.prueba_tecnica2.models.Empleado;
import sena.prueba_tecnica2.services.EmpleadoServiceImpl;

import java.util.List;


@RestController("/empleado")
@RequestMapping(value = "/empleado")
@CrossOrigin("*")
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceImpl empleadoServiceImpl;

    @GetMapping(path = { "/listarEmpleados", "empleados" })
    public List<Empleado> listarEmpleados() {
        return empleadoServiceImpl.getAllEmpleados();
    }

    @PostMapping("/add")
    public String addEmpleado(@RequestBody Empleado empleado) {
        return empleadoServiceImpl.addEmpleado(empleado);
    }

    @PutMapping("/update/{idEmpleado}")
    public ResponseEntity<Empleado> updateEmpleado(@PathVariable Integer idEmpleado, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoServiceImpl.updateEmpleado(idEmpleado, empleado);

        if (empleadoActualizado != null) {
            return new ResponseEntity<>(empleadoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
