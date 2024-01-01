package sena.prueba_tecnica2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.exceptions.EmpleadoNotFoundException;
import sena.prueba_tecnica2.models.Empleado;
import sena.prueba_tecnica2.repository.EmpleadoRepository;

import java.util.List;
import java.util.Optional;

import static java.util.regex.Pattern.matches;

@Service
public class EmpleadoServiceImpl implements EmpleadoService{

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Empleado> getAllEmpleados() {
        return empleadoRepository.findAll(Sort.by(Sort.Direction.DESC, "idEmpleado"));
    }

    @Override
    public String addEmpleado(Empleado empleado) {
        String contraseñaCodificada = passwordEncoder.encode(empleado.getPassword());
        empleado.setPassword(contraseñaCodificada);
        empleadoRepository.save(empleado);
        return "Successfully";
    }

    @Override
    public Empleado updateEmpleado(Integer idEmpleado, Empleado empleado) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findById(idEmpleado);

        if (empleadoOptional.isPresent()) {
            Empleado empleadoExistente = empleadoOptional.get();

            empleadoExistente.setNombre(empleado.getNombre());
            empleadoExistente.setApellidos(empleado.getApellidos());
            empleadoExistente.setEmail(empleado.getEmail());
            empleadoExistente.setTelefono(empleado.getTelefono());
            empleadoExistente.setNumeroDocumento(empleado.getNumeroDocumento());
            empleadoExistente.setRol(empleado.getRol());

            // Verificar si la contraseña ha cambiado
            if (!empleadoExistente.getPassword().equals(empleado.getPassword())) {
                String contraseñaCodificada = passwordEncoder.encode(empleado.getPassword());
                empleadoExistente.setPassword(contraseñaCodificada);
            }

            return empleadoRepository.save(empleadoExistente);
        } else {
            return null;
        }
    }

    @Override
    public Empleado getEmpleadoById(Integer idEmpleado) {
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(idEmpleado);

        if (optionalEmpleado.isPresent()) {
            return optionalEmpleado.get();
        } else {
            throw new EmpleadoNotFoundException("Empleado con ID " + idEmpleado + " no encontrado");
        }
    }


    @Override
    public Empleado findOne(Integer idEmpleado) {
        return empleadoRepository.findById(idEmpleado).orElse(null);
    }

    @Override
    public void delete(Integer IdEmpleado) {

    }

    @Override
    public boolean authenticate(String email, String password) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findByEmail(email);

        return empleadoOptional.map(empleado -> {
            return passwordEncoder.matches(password, empleado.getPassword());
        }).orElse(false);

    }

    @Override
    public String getRoleByEmail(String email) {
        Optional<Empleado> empleadoOptional = empleadoRepository.findByEmail(email);

        return empleadoOptional.map(empleado -> {
            return empleado.getRol() != null ? empleado.getRol().getNombreRol() : null;
        }).orElse(null);
    }

    public Optional<Empleado> findById(Integer id) {
        return empleadoRepository.findById(id);
    }
}
