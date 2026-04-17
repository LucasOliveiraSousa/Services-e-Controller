package com.hospitalsystem.controller;

import com.hospitalsystem.model.Consulta;
import com.hospitalsystem.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodos() {
        List<Consulta> consultas = consultaService.listarTodos();
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        Optional<Consulta> consulta = consultaService.buscarPorId(id);
        return consulta.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<Consulta>> buscarPorPacienteId(@PathVariable Long pacienteId) {
        List<Consulta> consultas = consultaService.buscarPorPacienteId(pacienteId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/medico/{medicoId}")
    public ResponseEntity<List<Consulta>> buscarPorMedicoId(@PathVariable Long medicoId) {
        List<Consulta> consultas = consultaService.buscarPorMedicoId(medicoId);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<Consulta>> buscarPorData(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Consulta> consultas = consultaService.buscarPorData(data);
        return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Consulta> criar(@RequestBody Consulta consulta) {
        Consulta novaConsulta = consultaService.criar(consulta);
        return new ResponseEntity<>(novaConsulta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> atualizar(@PathVariable Long id, @RequestBody Consulta consultaAtualizada) {
        Consulta consultaAtualizadaResponse = consultaService.atualizar(id, consultaAtualizada);
        if (consultaAtualizadaResponse != null) {
            return new ResponseEntity<>(consultaAtualizadaResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (consultaService.excluir(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}