package sena.prueba_tecnica2.repository;

import org.springframework.data.repository.CrudRepository;
import sena.prueba_tecnica2.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
