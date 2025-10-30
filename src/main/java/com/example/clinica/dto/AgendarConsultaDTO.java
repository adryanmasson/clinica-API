package com.example.clinica.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AgendarConsultaDTO {

    private Integer idPaciente;
    private Integer idMedico;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public AgendarConsultaDTO() {
    }

    public AgendarConsultaDTO(Integer idPaciente, Integer idMedico, LocalDate data, LocalTime horaInicio,
            LocalTime horaFim) {
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(LocalTime horaFim) {
        this.horaFim = horaFim;
    }
}
