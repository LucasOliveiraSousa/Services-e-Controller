package com.hospitalsystem.controller;

import com.hospitalsystem.model.Convenio;
import com.hospitalsystem.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/convenios")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConvenioController {
    @Autowired
    private ConvenioService convenioService;

    @GetMapping
    public ResponseEntity<List<Convenio>> listarTodos() {
        List<Convenio> convenios = convenioService.listarTodos();
        return new ResponseEntity<>(convenios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Convenio> buscarPorId(@PathVariable Long id) {
        Optional<Convenio> convenio = convenioService.buscarPorId(id);
        return convenio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Convenio> buscarPorNome(@PathVariable String nome) {
        Optional<Convenio> convenio = convenioService.buscarPorNome(nome);
        return convenio.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Convenio> criar(@RequestBody Convenio convenio) {
        Convenio novoConvenio = convenioService.criar(convenido);
        return new ResponseEntity<>(novoConvenio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenioAtualizado) {
        Convenio convenioAtualizadoResponse = convenioService.atualizar(id, convenioAtualizado);
        if (convenioAtualizadoResponse != null) {
            return new ResponseEntity<>(convenioAtualizadoResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (convenioService.excluir(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}