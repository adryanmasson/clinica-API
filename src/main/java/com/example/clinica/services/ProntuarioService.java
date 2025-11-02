package com.example.clinica.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clinica.dto.ProntuarioDTO;
import com.example.clinica.dto.AtualizarProntuarioDTO;
import com.example.clinica.dto.CriarProntuarioDTO;
import com.example.clinica.models.Prontuario;
import com.example.clinica.models.Consulta;
import com.example.clinica.repositories.ProntuarioRepository;
import com.example.clinica.repositories.ConsultaRepository;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ConsultaRepository consultaRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, ConsultaRepository consultaRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.consultaRepository = consultaRepository;
    }

    @Transactional
    public List<ProntuarioDTO> listarProntuarios() {
        List<Map<String, Object>> prontuarios = prontuarioRepository.listarProntuarios();
        return prontuarios.stream().map(ProntuarioDTO::fromMap).collect(Collectors.toList());
    }

    @Transactional
    public ProntuarioDTO buscarPorConsulta(Integer idConsulta) {
        Map<String, Object> m = prontuarioRepository.findDetalhadoByConsultaId(idConsulta);
        return ProntuarioDTO.fromMap(m);
    }

    @Transactional
    public ProntuarioDTO criarProntuario(CriarProntuarioDTO dto) {
        Integer idConsulta = dto.getIdConsulta();
        if (idConsulta == null) {
            throw new RuntimeException("idConsulta é obrigatório.");
        }

        Prontuario existente = prontuarioRepository.findByConsultaId(idConsulta);
        if (existente != null) {
            throw new RuntimeException("Já existe prontuário para essa consulta.");
        }

        Consulta consulta = consultaRepository.findById(idConsulta)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));

        Prontuario prontuario = new Prontuario();
        prontuario.setConsulta(consulta);
        prontuario.setAnamnese(dto.getAnamnese());
        prontuario.setDiagnostico(dto.getDiagnostico());
        prontuario.setPrescricao(dto.getPrescricao());
        prontuario.setDataRegistro(LocalDate.now());

        prontuarioRepository.save(prontuario);

        Map<String, Object> detalhado = prontuarioRepository.findDetalhadoByConsultaId(idConsulta);
        return ProntuarioDTO.fromMap(detalhado);
    }

    @Transactional
    public ProntuarioDTO atualizarProntuario(Integer id, AtualizarProntuarioDTO dados) {
        Optional<Prontuario> optionalProntuario = prontuarioRepository.findById(id);

        if (optionalProntuario.isEmpty()) {
            return null;
        }

        Prontuario prontuario = optionalProntuario.get();

        if (dados.getAnamnese() != null)
            prontuario.setAnamnese(dados.getAnamnese());
        if (dados.getDiagnostico() != null)
            prontuario.setDiagnostico(dados.getDiagnostico());
        if (dados.getPrescricao() != null)
            prontuario.setPrescricao(dados.getPrescricao());

        prontuarioRepository.save(prontuario);

        return ProntuarioDTO.fromEntity(prontuario);
    }

    @Transactional
    public boolean excluirProntuario(Integer idProntuario) {
        if (prontuarioRepository.existsById(idProntuario)) {
            prontuarioRepository.deleteById(idProntuario);
            return true;
        }
        return false;
    }

}
