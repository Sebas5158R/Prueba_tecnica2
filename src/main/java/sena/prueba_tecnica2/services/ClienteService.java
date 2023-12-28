package sena.prueba_tecnica2.services;

import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.models.Cliente;

import java.util.List;

public interface ClienteService {
    public List<Cliente> findAll();

    public void save(@Validated Cliente cliente);

    public Cliente findOne(Integer IdCliente);

    public void delete(Integer IdCliente);
}
