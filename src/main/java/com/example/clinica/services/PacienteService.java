package com.example.clinica.services;

import com.example.clinica.dto.ConsultaDTO;
import com.example.clinica.dto.HistoricoPacienteDTO;
import com.example.clinica.models.Consulta;
import com.example.clinica.models.Paciente;
import com.example.clinica.repositories.PacienteRepository;
import com.example.clinica.repositories.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final ConsultaRepository consultaRepository;

    public PacienteService(PacienteRepository pacienteRepository, ConsultaRepository consultaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.consultaRepository = consultaRepository;
    }

    public List<Paciente> listarPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente buscarPacientePorId(Integer id) {
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com ID: " + id));
    }

    public Paciente criarPaciente(Paciente paciente) {
        Optional<Paciente> pacienteExistente = pacienteRepository.findByCpf(paciente.getCpf());
        if (pacienteExistente.isPresent()) {
            throw new RuntimeException("Já existe paciente com esse CPF: " + paciente.getCpf());
        }
        return pacienteRepository.save(paciente);
    }

    public Paciente atualizarPaciente(Integer id, Paciente dadosAtualizados) {
        Paciente paciente = buscarPacientePorId(id);

        if (dadosAtualizados.getTelefone() != null) {
            paciente.setTelefone(dadosAtualizados.getTelefone());
        }
        if (dadosAtualizados.getEmail() != null) {
            paciente.setEmail(dadosAtualizados.getEmail());
        }
        if (dadosAtualizados.getLogradouro() != null) {
            paciente.setLogradouro(dadosAtualizados.getLogradouro());
        }

        return pacienteRepository.save(paciente);
    }

    public void excluirPaciente(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

        List<Consulta> consultas = consultaRepository.findByPacienteId(id);
        if (!consultas.isEmpty()) {
            throw new RuntimeException("Paciente possui consultas associadas e não pode ser excluído.");
        }

        pacienteRepository.delete(paciente);
    }

    public Integer calcularIdadePaciente(Integer idPaciente) {
        return pacienteRepository.calcularIdade(idPaciente);
    }

    @Transactional
    public List<HistoricoPacienteDTO> listarHistoricoPaciente(Integer idPaciente) {
        List<Map<String, Object>> historico = pacienteRepository.listarHistoricoPaciente(idPaciente);

        return historico.stream().map(h -> {
            HistoricoPacienteDTO dto = new HistoricoPacienteDTO();
            dto.setIdConsulta((Integer) h.get("idConsulta"));
            dto.setDataConsulta(((java.sql.Date) h.get("dataConsulta")).toLocalDate());
            dto.setHoraInicio(((java.sql.Time) h.get("horaInicio")).toLocalTime());
            dto.setHoraFim(((java.sql.Time) h.get("horaFim")).toLocalTime());
            dto.setStatusConsulta((String) h.get("status"));
            dto.setIdProntuario((Integer) h.get("idProntuario"));
            dto.setAnamnese((String) h.get("anamnese"));
            dto.setDiagnostico((String) h.get("diagnostico"));
            dto.setPrescricao((String) h.get("prescricao"));
            dto.setNomeMedico((String) h.get("nomeMedico"));
            return dto;
        }).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConsultaDTO> relatorioConsultasUltimosMeses(Integer idPaciente, Integer meses) {
        List<Map<String, Object>> consultas = consultaRepository.relatorioConsultasUltimosMeses(idPaciente, meses);

        return consultas.stream().map(c -> {
            ConsultaDTO dto = new ConsultaDTO();
            dto.setId((Integer) c.get("idConsulta"));
            dto.setDataConsulta(((java.sql.Date) c.get("dataConsulta")).toLocalDate());
            dto.setHoraInicio(((java.sql.Time) c.get("horaInicio")).toLocalTime());
            dto.setHoraFim(((java.sql.Time) c.get("horaFim")).toLocalTime());
            dto.setStatus((String) c.get("status"));
            dto.setNomeMedico((String) c.get("nomeMedico"));
            dto.setNomePaciente((String) c.get("nomePaciente"));
            return dto;
        }).collect(Collectors.toList());

    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> contarPacientesPorEspecialidade() {
        return pacienteRepository.contarPacientesPorEspecialidade();
    }

}
