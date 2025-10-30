package com.example.clinica.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicos")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Integer id_medico;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "crm", nullable = false, length = 20, unique = true)
    private String crm;

    @ManyToOne
    @JoinColumn(name = "fk_id_especialidade", nullable = false)
    private Especialidade especialidade;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate data_nascimento;

    @Column(name = "telefone", length = 17)
    private String telefone;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    public Integer getId_medico() {
        return id_medico;
    }

    public void setId_medico(Integer id_medico) {
        this.id_medico = id_medico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
