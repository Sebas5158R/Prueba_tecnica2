package sena.prueba_tecnica2.services;

import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.models.Empleado;

import java.util.List;

public interface EmpleadoService {

    public List<Empleado> getAllEmpleados();

    public String addEmpleado(Empleado empleado);

    public Empleado findOne(Integer IdEmpleado);

    public void delete(Integer IdEmpleado);

}
