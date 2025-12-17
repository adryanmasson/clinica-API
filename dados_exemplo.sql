-- Dados de exemplo para testar a API

-- Especialidades
INSERT INTO especialidades (nome, descricao) VALUES
('Cardiologia', 'Especialidade médica que cuida do coração'),
('Dermatologia', 'Especialidade médica que cuida da pele'),
('Ortopedia', 'Especialidade médica que cuida dos ossos e articulações'),
('Pediatria', 'Especialidade médica que cuida de crianças'),
('Neurologia', 'Especialidade médica que cuida do sistema nervoso');

-- Médicos
INSERT INTO medicos (nome, crm, fk_id_especialidade, data_nascimento, telefone, ativo) VALUES
('Dr. João Silva', 'CRM12345-SP', 1, '1980-05-15', '(11) 98765-4321', 1),
('Dra. Maria Santos', 'CRM23456-SP', 2, '1985-08-20', '(11) 97654-3210', 1),
('Dr. Pedro Costa', 'CRM34567-SP', 3, '1978-12-10', '(11) 96543-2109', 1),
('Dra. Ana Paula', 'CRM45678-SP', 4, '1990-03-25', '(11) 95432-1098', 1),
('Dr. Carlos Oliveira', 'CRM56789-SP', 5, '1975-07-30', '(11) 94321-0987', 1);

-- Pacientes
INSERT INTO pacientes (nome, sexo, cpf, data_nascimento, telefone, logradouro, email) VALUES
('José da Silva', 'M', '123.456.789-00', '1995-01-10', '(11) 91234-5678', 'Rua das Flores, 123', 'jose.silva@email.com'),
('Maria Souza', 'F', '234.567.890-11', '1988-06-15', '(11) 92345-6789', 'Av. Principal, 456', 'maria.souza@email.com'),
('Pedro Santos', 'M', '345.678.901-22', '2000-09-20', '(11) 93456-7890', 'Rua do Comércio, 789', 'pedro.santos@email.com'),
('Ana Costa', 'F', '456.789.012-33', '1992-12-25', '(11) 94567-8901', 'Travessa Verde, 321', 'ana.costa@email.com'),
('Lucas Oliveira', 'M', '567.890.123-44', '1985-03-30', '(11) 95678-9012', 'Alameda dos Anjos, 654', 'lucas.oliveira@email.com');

-- Consultas (alguns exemplos)
INSERT INTO consultas (fk_id_paciente, fk_id_medico, data_consulta, hora_inicio, hora_fim, status) VALUES
(1, 1, '2025-12-20', '09:00:00', '10:00:00', 'AGENDADA'),
(2, 2, '2025-12-20', '10:00:00', '11:00:00', 'AGENDADA'),
(3, 3, '2025-12-21', '14:00:00', '15:00:00', 'AGENDADA'),
(4, 4, '2025-12-22', '11:00:00', '12:00:00', 'AGENDADA'),
(5, 5, '2025-12-23', '15:00:00', '16:00:00', 'AGENDADA');

-- Prontuários (alguns exemplos) - Prontuário está ligado a CONSULTA, não a paciente
INSERT INTO prontuarios (fk_id_consulta, anamnese, diagnostico, prescricao, data_registro) VALUES
(1, 'Paciente relata dores no peito há 3 dias', 'Suspeita de angina', 'Repouso e acompanhamento cardiológico', CAST(GETDATE() AS DATE)),
(2, 'Manchas vermelhas na pele há 1 semana', 'Dermatite de contato', 'Pomada antialérgica 2x ao dia', CAST(GETDATE() AS DATE)),
(3, 'Dor no joelho após queda', 'Entorse de joelho grau II', 'Imobilização e fisioterapia', CAST(GETDATE() AS DATE));
