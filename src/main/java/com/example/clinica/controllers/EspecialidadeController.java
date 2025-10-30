package com.example.clinica.controllers;

import com.example.clinica.dto.ApiResponse;
import com.example.clinica.models.Especialidade;
import com.example.clinica.services.EspecialidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Especialidade>>> listarEspecialidades() {
        List<Especialidade> especialidades = especialidadeService.listarEspecialidades();
        ApiResponse<List<Especialidade>> body = ApiResponse.sucesso("Especialidades listadas com sucesso.",
                especialidades);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Especialidade>> buscarEspecialidadePorId(@PathVariable Integer id) {
        Especialidade especialidade = especialidadeService.buscarEspecialidadePorId(id);
        ApiResponse<Especialidade> body = ApiResponse.sucesso("Especialidade encontrada com sucesso.", especialidade);
        return ResponseEntity.ok(body);
    }
}
