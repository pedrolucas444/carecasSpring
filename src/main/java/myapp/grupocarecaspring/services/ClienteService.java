package myapp.grupocarecaspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myapp.grupocarecaspring.entities.Cliente;
import myapp.grupocarecaspring.repositories.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> encontrarClientePorId(int id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(int id) {
        clienteRepository.deleteById(id);
    }
}
