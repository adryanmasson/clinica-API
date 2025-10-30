package com.example.clinica.services;

import com.example.clinica.dto.ConsultaDTO;
import com.example.clinica.models.Consulta;
import com.example.clinica.models.ConsultaStatus;
import com.example.clinica.models.Medico;
import com.example.clinica.models.Paciente;
import com.example.clinica.repositories.ConsultaRepository;
import com.example.clinica.repositories.MedicoRepository;
import com.example.clinica.repositories.PacienteRepository;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository) {
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll().stream()
                .map(c -> new ConsultaDTO(
                        c.getId_consulta(),
                        c.getPaciente().getNome(),
                        medicoRepository.findById(c.getFkIdMedico())
                                .map(m -> m.getNome())
                                .orElse("Médico não encontrado"),
                        c.getData_consulta(),
                        c.getHora_inicio(),
                        c.getHora_fim(),
                        c.getStatus().name()))
                .collect(Collectors.toList());
    }

    public ConsultaDTO buscarConsultaPorId(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));

        String nomePaciente = consulta.getPaciente().getNome();

        Medico medico = medicoRepository.findById(consulta.getFkIdMedico())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));
        String nomeMedico = medico.getNome();

        return new ConsultaDTO(
                consulta.getId_consulta(),
                nomePaciente,
                nomeMedico,
                consulta.getData_consulta(),
                consulta.getHora_inicio(),
                consulta.getHora_fim(),
                consulta.getStatus().name());
    }

    @Transactional
    public void agendarConsulta(Integer idPaciente, Integer idMedico,
            LocalDate data, LocalTime horaInicio, LocalTime horaFim) {

        try {
            consultaRepository.criarConsulta(idPaciente, idMedico, data, horaInicio, horaFim);
        } catch (DataAccessException ex) {
            Throwable cause = ex.getMostSpecificCause();
            String mensagem = cause != null ? cause.getMessage() : ex.getMessage();

            throw new RuntimeException(mensagem);
        }
    }

}
