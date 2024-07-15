package myapp.grupocarecaspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import myapp.grupocarecaspring.entities.Mesa;
import myapp.grupocarecaspring.repositories.MesaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> encontrarMesaPorId(int id) {
        return mesaRepository.findById(id);
    }

    public Mesa salvarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deletarMesa(int id) {
        mesaRepository.deleteById(id);
    }
}
