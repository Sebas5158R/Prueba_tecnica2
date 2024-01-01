package sena.prueba_tecnica2.services;

import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.models.Empleado;

import java.util.List;

public interface EmpleadoService {

    public List<Empleado> getAllEmpleados();

    public String addEmpleado(Empleado empleado);

    public Empleado updateEmpleado(Integer idEmpleado, Empleado empleado);

    public Empleado findOne(Integer idEmpleado);

    public void delete(Integer IdEmpleado);

    public boolean authenticate(String email, String password);

}
