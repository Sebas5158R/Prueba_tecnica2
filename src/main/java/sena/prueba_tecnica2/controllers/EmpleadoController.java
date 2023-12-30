package sena.prueba_tecnica2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

//    @GetMapping("/registrarEmpleado")
//    @ResponseBody
//    public String form(Model m) {
//        return "Acá estara el formulario para registrar empleados";
//    }

    @PostMapping("/add")
    public String addEmpleado(@RequestBody Empleado empleado) {
        return empleadoServiceImpl.addEmpleado(empleado);
    }

}
