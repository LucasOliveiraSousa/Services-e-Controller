package com.hospitalsystem.repository;
import com.hospitalsystem.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    List<Prontuario> findByPacienteId(Long pacienteId);
}