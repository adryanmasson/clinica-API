package com.example.clinica.repositories;

import java.sql.Date;
import java.sql.Time;

public interface ConsultaDetalhadaProjection {
    Integer getId();

    Date getData_consulta();

    Time getHora_inicio();

    Time getHora_fim();

    String getStatus();

    String getNome_paciente();

    String getNome_medico();
}
