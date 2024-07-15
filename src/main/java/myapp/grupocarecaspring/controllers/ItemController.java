package myapp.grupocarecaspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import myapp.grupocarecaspring.entities.Item;
import myapp.grupocarecaspring.services.ItemService;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> listarItens() {
        List<Item> itens = itemService.listarItens();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> encontrarItemPorId(@PathVariable int id) {
        return itemService.encontrarItemPorId(id)
                .map(item -> new ResponseEntity<>(item, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Item> salvarItem(@RequestBody Item item) {
        Item novoItem = itemService.salvarItem(item);
        return new ResponseEntity<>(novoItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable int id) {
        itemService.deletarItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
