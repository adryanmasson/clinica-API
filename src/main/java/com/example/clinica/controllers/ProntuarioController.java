package com.example.clinica.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica.dto.ProntuarioDTO;
import com.example.clinica.dto.CriarProntuarioDTO;
import com.example.clinica.services.ProntuarioService;
import com.example.clinica.dto.ApiResponse;
import com.example.clinica.dto.AtualizarProntuarioDTO;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProntuarioDTO>>> listarProntuarios() {
        List<ProntuarioDTO> prontuarios = prontuarioService.listarProntuarios();
        String mensagem = prontuarios.isEmpty()
                ? "Nenhum prontuário encontrado."
                : "Prontuários retornados com sucesso.";
        return ResponseEntity.ok(ApiResponse.sucesso(mensagem, prontuarios));
    }

    @GetMapping("/consulta/{idConsulta}")
    public ResponseEntity<ApiResponse<ProntuarioDTO>> buscarPorConsulta(@PathVariable Integer idConsulta) {
        ProntuarioDTO dto = prontuarioService.buscarPorConsulta(idConsulta);
        if (dto == null) {
            return ResponseEntity.status(404)
                    .body(ApiResponse.erro("Prontuário não encontrado para a consulta " + idConsulta));
        }
        return ResponseEntity.ok(ApiResponse.sucesso("Prontuário retornado com sucesso.", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProntuarioDTO>> criarProntuario(@RequestBody CriarProntuarioDTO dto) {
        ProntuarioDTO criado = prontuarioService.criarProntuario(dto);
        return ResponseEntity.status(201).body(ApiResponse.sucesso("Prontuário criado com sucesso.", criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProntuarioDTO>> atualizarProntuario(
            @PathVariable Integer id,
            @RequestBody AtualizarProntuarioDTO dados) {
        ProntuarioDTO prontuarioAtualizado = prontuarioService.atualizarProntuario(id, dados);
        if (prontuarioAtualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ApiResponse.sucesso("Prontuário atualizado com sucesso.", prontuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> excluirProntuario(@PathVariable Integer id) {
        boolean excluido = prontuarioService.excluirProntuario(id);
        if (excluido) {
            return ResponseEntity.ok(ApiResponse.sucesso("Prontuário excluído com sucesso.", null));
        } else {
            return ResponseEntity.status(404).body(ApiResponse.erro("Prontuário não encontrado."));
        }
    }

}
