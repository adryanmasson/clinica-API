package com.example.clinica.controllers;

import com.example.clinica.models.Paciente;
import com.example.clinica.services.PacienteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PacienteController {
    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("api/pacientes")
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }
}
