package com.hospitalsystem.controller;

import com.hospitalsystem.exception.ResourceNotFoundException;
import com.hospitalsystem.model.Medico;
import com.hospitalsystem.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<List<Medico>> listarTodos() {
        List<Medico> medicos = medicoService.listarTodos();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> buscarPorId(@PathVariable Long id) {
        Medico medico = medicoService.buscarPorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com ID " + id + " não encontrado"));
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @GetMapping("/crm/{crm}")
    public ResponseEntity<Medico> buscarPorCrm(@PathVariable String crm) {
        Medico medico = medicoService.buscarPorCrm(crm)
                .orElseThrow(() -> new ResourceNotFoundException("Médico com CRM '" + crm + "' não encontrado"));
        return new ResponseEntity<>(medico, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Medico> criar(@RequestBody Medico medico) {
        Medico novoMedico = medicoService.criar(medico);
        return new ResponseEntity<>(novoMedico, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> atualizar(@PathVariable Long id, @RequestBody Medico medicoAtualizado) {
        Medico medicoAtualizadoResponse = medicoService.atualizar(id, medicoAtualizado);
        if (medicoAtualizadoResponse != null) {
            return new ResponseEntity<>(medicoAtualizadoResponse, HttpStatus.OK);
        }
        throw new ResourceNotFoundException("Médico com ID " + id + " não encontrado para atualização");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (medicoService.excluir(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new ResourceNotFoundException("Médico com ID " + id + " não encontrado para exclusão");
    }
}
