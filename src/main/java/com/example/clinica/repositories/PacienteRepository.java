package com.example.clinica.repositories;

import com.example.clinica.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
    boolean existsByCpf(String cpf);

    Optional<Paciente> findByCpf(String cpf);

    @Query(value = "SELECT calcular_idade(data_nascimento) FROM pacientes WHERE id_paciente = ?1", nativeQuery = true)
    Integer calcularIdade(Integer idPaciente);

    @Query(value = """
                SELECT c.id_consulta AS idConsulta,
                       c.data_consulta AS dataConsulta,
                       c.hora_inicio AS horaInicio,
                       c.hora_fim AS horaFim,
                       c.status AS status,
                       p.id_prontuario AS idProntuario,
                       p.anamnese AS anamnese,
                       p.diagnostico AS diagnostico,
                       p.prescricao AS prescricao,
                       m.nome AS nomeMedico
                FROM consultas c
                LEFT JOIN prontuarios p ON p.fk_id_consulta = c.id_consulta
                JOIN medicos m ON m.id_medico = c.fk_id_medico
                WHERE c.fk_id_paciente = :idPaciente
                ORDER BY c.data_consulta DESC, c.hora_inicio DESC
            """, nativeQuery = true)
    List<Map<String, Object>> listarHistoricoPaciente(@Param("idPaciente") Integer idPaciente);

}
