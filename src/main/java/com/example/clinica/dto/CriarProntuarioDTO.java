package com.example.clinica.dto;

import java.io.Serializable;

public class CriarProntuarioDTO implements Serializable {

    private Integer idConsulta;
    private String anamnese;
    private String diagnostico;
    private String prescricao;

    public CriarProntuarioDTO() {
    }

    public CriarProntuarioDTO(Integer idConsulta, String anamnese, String diagnostico, String prescricao) {
        this.idConsulta = idConsulta;
        this.anamnese = anamnese;
        this.diagnostico = diagnostico;
        this.prescricao = prescricao;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(String anamnese) {
        this.anamnese = anamnese;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getPrescricao() {
        return prescricao;
    }

    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }
}
