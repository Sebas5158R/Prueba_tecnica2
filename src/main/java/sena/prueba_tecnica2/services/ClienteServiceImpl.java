package sena.prueba_tecnica2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import sena.prueba_tecnica2.models.Cliente;
import sena.prueba_tecnica2.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteRepository.findAll();
    }

    @Override
    public void save(@Validated Cliente cliente) {
        clienteRepository.save(cliente);
    }

    @Override
    public Cliente findOne(Integer idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    @Override
    public void delete(Integer idCliente) {
        clienteRepository.deleteById(idCliente);
    }

}
