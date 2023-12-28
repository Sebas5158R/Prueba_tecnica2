package sena.prueba_tecnica2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.models.Empleado;
import sena.prueba_tecnica2.repository.EmpleadoRepository;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll(Sort.by(Sort.Direction.DESC, "idEmpleado"));
    }

    @Override
    public String addEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
        return "Successfully";
    }

    @Override
    public Empleado findOne(Integer IdEmpleado) {
        return null;
    }

    @Override
    public void delete(Integer IdEmpleado) {

    }


}
