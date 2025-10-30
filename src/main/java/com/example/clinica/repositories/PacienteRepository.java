package com.example.clinica.repositories;

import com.example.clinica.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByCpf(String cpf);

    Optional<Paciente> findByCpf(String cpf);

    @Query(value = "SELECT calcular_idade(data_nascimento) FROM pacientes WHERE id_paciente = ?1", nativeQuery = true)
    Integer calcularIdade(Integer idPaciente);
}
