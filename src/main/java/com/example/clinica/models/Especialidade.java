package com.example.clinica.models;

import jakarta.persistence.*;

@Entity
@Table(name = "especialidades", uniqueConstraints = {
        @UniqueConstraint(columnNames = "nome")
})
public class Especialidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Integer id_especialidade;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    public Especialidade() {
    }

    public Especialidade(Integer id_especialidade, String nome, String descricao) {
        this.id_especialidade = id_especialidade;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId_especialidade() {
        return id_especialidade;
    }

    public void setId_especialidade(Integer id_especialidade) {
        this.id_especialidade = id_especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
