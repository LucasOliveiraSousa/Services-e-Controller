package com.hospitalsystem.controller;

import com.hospitalsystem.exception.ResourceNotFoundException;
import com.hospitalsystem.model.Convenio;
import com.hospitalsystem.service.ConvenioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Convenio convenio = convenioService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio com ID " + id + " não encontrado"));
        return new ResponseEntity<>(convenio, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Convenio> buscarPorNome(@PathVariable String nome) {
        Convenio convenio = convenioService.buscarPorNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Convênio com nome '" + nome + "' não encontrado"));
        return new ResponseEntity<>(convenio, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Convenio> criar(@RequestBody Convenio convenio) {
        Convenio novoConvenio = convenioService.criar(convenio);
        return new ResponseEntity<>(novoConvenio, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Convenio> atualizar(@PathVariable Long id, @RequestBody Convenio convenioAtualizado) {
        Convenio convenioAtualizadoResponse = convenioService.atualizar(id, convenioAtualizado);
        if (convenioAtualizadoResponse != null) {
            return new ResponseEntity<>(convenioAtualizadoResponse, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Convênio com ID " + id + " não encontrado para atualização");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (convenioService.excluir(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResourceNotFoundException("Convênio com ID " + id + " não encontrado para exclusão");
    }
}
