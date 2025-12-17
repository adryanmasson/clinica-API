# 🏥 Campus Clinic - Medical Management System

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen?style=for-the-badge&logo=spring)
![SQL Server](https://img.shields.io/badge/SQL%20Server-2019+-CC2927?style=for-the-badge&logo=microsoft-sql-server)
![Azure](https://img.shields.io/badge/Azure-Deployed-0078D4?style=for-the-badge&logo=microsoft-azure)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

**Complete medical clinic management system with RESTful API, appointment scheduling, electronic medical records, and automated audit logging.**

[🌐 Live Demo](https://clinica-api-adryan.azurewebsites.net/api/especialidades) | [📖 Documentation](#-api-endpoints) | [🚀 Deploy](#-azure-deployment)

</div>

---

## 📋 About the Project

Complete management system for medical clinics, developed with **Spring Boot** and **SQL Server**, providing full control over:

- 👨‍⚕️ **Doctors and Medical Specialties Management**
- 👤 **Patient Registration and Medical History**
- 📅 **Appointment Scheduling and Management**
- 📝 **Electronic Medical Records with Automatic Audit Trail**
- 🔍 **Advanced Queries and Reports**

### 🎯 Technical Highlights

- ✅ **RESTful Architecture** with standardized response patterns
- ✅ **Complete Migration** from MySQL to SQL Server with optimizations
- ✅ **Native Stored Procedures & Functions** in SQL Server
- ✅ **Audit Triggers** for tracking medical record changes
- ✅ **Automated Deployment** via GitHub Actions to Azure App Service
- ✅ **Business Validations** at multiple layers (Database + Application)
- ✅ **Spring Security** configured with CORS for frontend integration

---

## 🛠️ Technology Stack

### Backend
- **Java 17** - LTS with modern features
- **Spring Boot 3.4.5** - Main framework
- **Spring Data JPA** - Persistence and ORM
- **Spring Security** - Authentication and authorization
- **Maven** - Dependency management

### Database
- **Microsoft SQL Server** - Primary database
- **T-SQL** - Custom procedures, functions, and triggers
- **Azure SQL Database** - Cloud hosting

### DevOps & Cloud
- **Azure App Service** - Application hosting
- **GitHub Actions** - Automated CI/CD
- **Azure CLI** - Infrastructure management

---

## 🏗️ Architecture

```
┌─────────────────┐
│   Frontend      │
│  (Angular/React)│
└────────┬────────┘
         │ HTTPS
         ▼
┌─────────────────────────────────────────┐
│         Spring Boot REST API            │
├─────────────────────────────────────────┤
│  ┌──────────────┐  ┌─────────────────┐ │
│  │ Controllers  │  │  Security       │ │
│  │  (REST)      │  │  (CORS/Auth)    │ │
│  └──────┬───────┘  └─────────────────┘ │
│         │                                │
│  ┌──────▼───────────────────────────┐  │
│  │       Services Layer             │  │
│  │  (Business Logic & Validation)   │  │
│  └──────┬───────────────────────────┘  │
│         │                                │
│  ┌──────▼───────────────────────────┐  │
│  │    Repositories (JPA/Hibernate)  │  │
│  └──────┬───────────────────────────┘  │
└─────────┼───────────────────────────────┘
          │ JDBC
          ▼
┌─────────────────────────────────────────┐
│      SQL Server Database (Azure)        │
├─────────────────────────────────────────┤
│  • Tabelas: especialidades, medicos,   │
│    pacientes, consultas, prontuarios   │
│  • Functions: calcular_idade            │
│  • Procedures: criar_consulta           │
│  • Triggers: auditoria_prontuario      │
└─────────────────────────────────────────┘
```

---

## 📚 Endpoints da API

### Base URL
```
https://clinica-api-adryan.azurewebsites.net
```

### 🩺 Especialidades Médicas

#### Listar Todas as Especialidades
```http
GET /api/especialidades
```

**Resposta (200 OK)**
```json
{
  "status": "sucesso",
  "mensagem": "Especialidades listadas com sucesso.",
  "data": [
    {
      "id_especialidade": 1,
      "nome": "Cardiologia",
      "descricao": "Especialidade médica que cuida do coração"
    }
  ]
}
```

#### Buscar Especialidade por ID
```http
GET /api/especialidades/{id}
```

#### Criar Nova Especialidade
```http
POST /api/especialidades
Content-Type: application/json

{
  "nome": "Cardiologia",
  "descricao": "Especialidade médica que cuida do coração"
}
```

#### Atualizar Especialidade
```http
PUT /api/especialidades/{id}
Content-Type: application/json

{
  "nome": "Cardiologia Clínica",
  "descricao": "Descrição atualizada"
}
```

#### Deletar Especialidade
```http
DELETE /api/especialidades/{id}
```

---

### 👨‍⚕️ Médicos

#### Listar Todos os Médicos
```http
GET /api/medicos
```

**Resposta (200 OK)**
```json
{
  "status": "sucesso",
  "mensagem": "Médicos listados com sucesso.",
  "data": [
    {
      "id": 1,
      "nome": "Dr. João Silva",
      "crm": "CRM12345-SP",
      "especialidade": {
        "id": 1,
        "nome": "Cardiologia"
      },
      "dataNascimento": "1980-05-15",
      "telefone": "(11) 98765-4321",
      "idade": 45,
      "ativo": true
    }
  ]
}
```

#### Buscar Médico por ID
```http
GET /api/medicos/{id}
```

#### Buscar Médicos por Especialidade
```http
GET /api/medicos?especialidade={especialidade_id}
```

#### Cadastrar Novo Médico
```http
POST /api/medicos
Content-Type: application/json

{
  "nome": "Dr. João Silva",
  "crm": "CRM12345-SP",
  "fkIdEspecialidade": 1,
  "dataNascimento": "1980-05-15",
  "telefone": "(11) 98765-4321"
}
```

#### Atualizar Médico
```http
PUT /api/medicos/{id}
Content-Type: application/json

{
  "nome": "Dr. João Silva Jr.",
  "telefone": "(11) 98765-9999"
}
```

#### Desativar/Ativar Médico
```http
PATCH /api/medicos/{id}/status
Content-Type: application/json

{
  "ativo": false
}
```

---

### 👤 Pacientes

#### Listar Todos os Pacientes
```http
GET /api/pacientes
```

**Resposta (200 OK)**
```json
{
  "status": "sucesso",
  "mensagem": "Pacientes listados com sucesso.",
  "data": [
    {
      "id": 1,
      "nome": "José da Silva",
      "cpf": "123.456.789-00",
      "sexo": "M",
      "dataNascimento": "1995-01-10",
      "idade": 30,
      "telefone": "(11) 91234-5678",
      "email": "jose.silva@email.com",
      "logradouro": "Rua das Flores, 123"
    }
  ]
}
```

#### Buscar Paciente por ID
```http
GET /api/pacientes/{id}
```

#### Buscar Paciente por CPF
```http
GET /api/pacientes/cpf/{cpf}
```

#### Cadastrar Novo Paciente
```http
POST /api/pacientes
Content-Type: application/json

{
  "nome": "José da Silva",
  "cpf": "123.456.789-00",
  "sexo": "M",
  "dataNascimento": "1995-01-10",
  "telefone": "(11) 91234-5678",
  "email": "jose.silva@email.com",
  "logradouro": "Rua das Flores, 123"
}
```

#### Atualizar Paciente
```http
PUT /api/pacientes/{id}
Content-Type: application/json

{
  "telefone": "(11) 99999-9999",
  "email": "novo.email@email.com"
}
```

---

### 📅 Consultas

#### Listar Todas as Consultas
```http
GET /api/consultas
```

**Resposta (200 OK)**
```json
{
  "status": "sucesso",
  "mensagem": "Consultas listadas com sucesso.",
  "data": [
    {
      "id": 1,
      "paciente": {
        "id": 1,
        "nome": "José da Silva"
      },
      "medico": {
        "id": 1,
        "nome": "Dr. João Silva",
        "especialidade": "Cardiologia"
      },
      "dataConsulta": "2025-12-20",
      "horaInicio": "09:00:00",
      "horaFim": "10:00:00",
      "status": "AGENDADA"
    }
  ]
}
```

#### Buscar Consultas de um Paciente
```http
GET /api/consultas?paciente={paciente_id}
```

#### Buscar Consultas de um Médico
```http
GET /api/consultas?medico={medico_id}
```

#### Buscar Consultas por Data
```http
GET /api/consultas?data={yyyy-MM-dd}
```

#### Buscar Consultas por Status
```http
GET /api/consultas?status={AGENDADA|REALIZADA|CANCELADA}
```

#### Agendar Nova Consulta
```http
POST /api/consultas
Content-Type: application/json

{
  "fkIdPaciente": 1,
  "fkIdMedico": 1,
  "dataConsulta": "2025-12-20",
  "horaInicio": "09:00:00",
  "horaFim": "10:00:00"
}
```

#### Atualizar Status da Consulta
```http
PATCH /api/consultas/{id}/status
Content-Type: application/json

{
  "status": "REALIZADA"
}
```

#### Cancelar Consulta
```http
DELETE /api/consultas/{id}
```

---

### 📝 Prontuários

#### Listar Todos os Prontuários
```http
GET /api/prontuarios
```

**Resposta (200 OK)**
```json
{
  "status": "sucesso",
  "mensagem": "Prontuários listados com sucesso.",
  "data": [
    {
      "id": 1,
      "consulta": {
        "id": 1,
        "data": "2025-12-20"
      },
      "anamnese": "Paciente relata dores no peito há 3 dias",
      "diagnostico": "Suspeita de angina",
      "prescricao": "Repouso e acompanhamento cardiológico",
      "dataRegistro": "2025-12-17"
    }
  ]
}
```

#### Buscar Prontuário por ID
```http
GET /api/prontuarios/{id}
```

#### Buscar Histórico Completo de um Paciente
```http
GET /api/prontuarios/paciente/{paciente_id}
```

**Retorna**: Todas as consultas e prontuários do paciente com informações de médicos e especialidades.

#### Criar Novo Prontuário
```http
POST /api/prontuarios
Content-Type: application/json

{
  "fkIdConsulta": 1,
  "anamnese": "Paciente relata...",
  "diagnostico": "Hipótese diagnóstica...",
  "prescricao": "Medicação prescrita..."
}
```

#### Atualizar Prontuário
```http
PUT /api/prontuarios/{id}
Content-Type: application/json

{
  "diagnostico": "Diagnóstico atualizado",
  "prescricao": "Nova prescrição"
}
```

> **⚠️ Importante**: Todas as alterações em prontuários são registradas automaticamente na tabela de auditoria através de um trigger no banco de dados.

---

## 🗄️ Modelo de Dados

### Tabelas Principais

- **`especialidades`** - Especialidades médicas (Cardiologia, Dermatologia, etc.)
- **`medicos`** - Cadastro de médicos com CRM e especialidade
- **`pacientes`** - Cadastro de pacientes com dados pessoais e de contato
- **`consultas`** - Agendamentos de consultas médicas
- **`prontuarios`** - Prontuários eletrônicos vinculados a consultas
- **`auditoria_prontuario`** - Histórico de alterações em prontuários

### Functions e Procedures

#### Function: `dbo.calcular_idade`
Calcula a idade de uma pessoa baseada na data de nascimento.

```sql
SELECT dbo.calcular_idade('1995-01-10') AS idade
-- Retorna: 30
```

#### Stored Procedure: `dbo.criar_consulta`
Cria uma nova consulta com validações de negócio integradas.

```sql
EXEC criar_consulta 
  @id_paciente = 1,
  @id_medico = 1,
  @data = '2025-12-20',
  @hora_inicio = '10:00',
  @hora_fim = '11:00'
```

#### Trigger: `trg_auditoria_prontuario_update`
Registra automaticamente todas as alterações em prontuários na tabela de auditoria.

---

## 🚀 Como Executar Localmente

### Pré-requisitos

- ☕ **Java 17** ou superior
- 📦 **Maven 3.8+**
- 🗄️ **SQL Server 2019+** (ou LocalDB)
- 🔧 **IDE** (IntelliJ IDEA, Eclipse, VS Code)

### Passos

1. **Clone o repositório**
```bash
git clone https://github.com/adryanmasson/clinica-API.git
cd clinica-API
```

2. **Configure o banco de dados**

Crie um banco de dados SQL Server:
```sql
CREATE DATABASE clinica_do_campus;
```

Execute o script de schema:
```bash
sqlcmd -S localhost -d clinica_do_campus -i clinica_do_campus.sql
```

(Opcional) Popule com dados de exemplo:
```bash
sqlcmd -S localhost -d clinica_do_campus -i dados_exemplo.sql
```

3. **Configure as variáveis de ambiente**

Crie um arquivo `.env` ou configure no sistema:
```bash
DB_URL=jdbc:sqlserver://localhost:1433;database=clinica_do_campus;encrypt=false
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

4. **Compile e execute**
```bash
mvn clean package
java -jar target/clinica-0.0.1-SNAPSHOT.jar
```

5. **Acesse a API**
```
http://localhost:8080/api/especialidades
```

---

## ☁️ Deploy no Azure

Este projeto está configurado para deploy automatizado no **Azure App Service** através de **GitHub Actions**.

### Configuração do CI/CD

O workflow `.github/workflows/main_clinica-api-adryan.yml` automatiza:

1. ✅ **Build** do projeto com Maven
2. ✅ **Testes** automatizados
3. ✅ **Deploy** para Azure App Service
4. ✅ **Verificação** de saúde da aplicação

### Variáveis de Ambiente no Azure

Configure no Azure Portal (App Service → Configuration):

```
DB_URL=jdbc:sqlserver://seu-servidor.database.windows.net:1433;database=clinica_do_campus;encrypt=true
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
```

### Infraestrutura Azure

- **App Service**: Plan Basic B1
- **Azure SQL Database**: GeneralPurpose Gen5 (2 vCores)
- **Region**: West US 2

---

## 🧪 Testes

### Executar Testes
```bash
mvn test
```

### Cobertura de Testes
```bash
mvn test jacoco:report
```

> **Nota**: Testes de integração com Testcontainers foram removidos devido a incompatibilidades com Windows. Recomendamos testes manuais ou uso de ambiente Linux para testes de integração.

---

## 📊 Funcionalidades Futuras

- [ ] Autenticação JWT com refresh tokens
- [ ] Sistema de notificações (email/SMS) para consultas
- [ ] Integração com calendário (Google Calendar, Outlook)
- [ ] Dashboard com métricas e relatórios
- [ ] Sistema de permissões por perfil (Admin, Médico, Recepcionista)
- [ ] Anexos em prontuários (exames, laudos)
- [ ] Busca avançada com filtros múltiplos
- [ ] Exportação de relatórios (PDF, Excel)
- [ ] API de teleconsulta
- [ ] Sistema de filas de espera

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Para contribuir:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👨‍💻 Autor

**Adryan Masson**

- GitHub: [@adryanmasson](https://github.com/adryanmasson)
- LinkedIn: [Adryan Masson](https://linkedin.com/in/adryanmasson)
- Email: adryanpereiramasson@gmail.com

---

## 🙏 Agradecimentos

- Spring Boot Team pela excelente documentação
- Comunidade Microsoft Azure pelo suporte
- Colegas de curso pela colaboração e feedback

---

<div align="center">

**⭐ Se este projeto foi útil para você, considere dar uma estrela!**

Desenvolvido com ☕ e ❤️ por [Adryan Masson](https://github.com/adryanmasson)

</div>
