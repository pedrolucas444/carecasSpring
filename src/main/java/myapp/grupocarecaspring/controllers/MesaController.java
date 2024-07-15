package myapp.grupocarecaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import myapp.grupocarecaspring.entities.Mesa;
import myapp.grupocarecaspring.services.MesaService;

import java.util.List;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<Mesa>> listarMesas() {
        List<Mesa> mesas = mesaService.listarMesas();
        return new ResponseEntity<>(mesas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> encontrarMesaPorId(@PathVariable int id) {
        return mesaService.encontrarMesaPorId(id)
                .map(mesa -> new ResponseEntity<>(mesa, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Mesa> salvarMesa(@RequestBody Mesa mesa) {
        Mesa novaMesa = mesaService.salvarMesa(mesa);
        return new ResponseEntity<>(novaMesa, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable int id) {
        mesaService.deletarMesa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
