# Simula API – Sistema Gerenciador de Simulados

API REST em **Java 21** e **Spring Boot 3.2** para cadastro de questões, matérias, assuntos, tópicos, órgãos, bancas, cargos e montagem de simulados com exportação em DOCX e CSV.

## Requisitos

- Java 21
- Maven 3.8+

## Executar

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`. O console H2 fica em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:simulados`).

## Endpoints

### Questões
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/questoes | Listar (filtros: materiaId, assuntoId, topicoId, orgaoId, bancaId, cargoId, ano) |
| GET | /api/questoes/:id | Detalhes |
| POST | /api/questoes | Criar |
| PUT | /api/questoes/:id | Atualizar |
| DELETE | /api/questoes/:id | Deletar |

### Matérias, Assuntos, Tópicos, Órgãos, Bancas, Cargos
- **GET** /api/materias, /api/assuntos, /api/topicos, /api/orgaos, /api/bancas, /api/cargos – listar (com paginação)
- **GET** /api/.../:id – detalhes
- **POST** /api/... – criar
- **PUT** /api/.../:id – atualizar
- **DELETE** /api/.../:id – deletar

Filtros opcionais: assuntos por `materiaId`, tópicos por `assuntoId`, cargos por `orgaoId`.

### Simulados
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/simulados | Listar (filtros: cargoId, orgaoId, ano) |
| GET | /api/simulados/:id | Detalhes (com questões) |
| POST | /api/simulados | Criar (incluir `simuladosQuestoes`: lista de `{ questao: { id }, ordem }`) |
| PUT | /api/simulados/:id | Atualizar |
| DELETE | /api/simulados/:id | Deletar |
| GET | /api/simulados/:id/gerar-docx | Download do simulado em DOCX |

### Dashboard e Export
- **GET** /api/dashboard/estatisticas – totais (questões, matérias, assuntos, tópicos, órgãos, bancas, cargos, simulados)
- **GET** /api/export/csv – exportar todos os dados em CSV (UTF-8)

## Exemplo de payloads

**Criar questão** (POST /api/questoes):
```json
{
  "enunciado": "Qual a capital do Brasil?",
  "alternativaA": "Rio de Janeiro",
  "alternativaB": "Brasília",
  "alternativaC": "São Paulo",
  "alternativaD": "Salvador",
  "alternativaE": "Belo Horizonte",
  "respostaCorreta": "B",
  "materia": { "id": 1 },
  "ano": 2024
}
```

**Criar simulado com questões** (POST /api/simulados):
```json
{
  "titulo": "Simulado Concurso 2024",
  "cargo": { "id": 1 },
  "orgao": { "id": 1 },
  "ano": 2024,
  "ordemMaterias": [1, 2, 3],
  "simuladosQuestoes": [
    { "questao": { "id": 1 }, "ordem": 1 },
    { "questao": { "id": 2 }, "ordem": 2 }
  ]
}
```

## Banco de dados

- **Desenvolvimento:** H2 em memória (`application.yml`).
- **Produção:** use o perfil `prod` e configure `spring.datasource.url` para PostgreSQL.

## Estrutura das tabelas

- **questoes** – enunciado, alternativas A–E, resposta_correta, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano
- **materias**, **assuntos** (materia_id), **topicos** (assunto_id)
- **orgaos**, **bancas**, **cargos** (orgao_id)
- **simulados** – titulo, cargo_id, orgao_id, ano, data_criacao, ordem_materias (JSON)
- **simulados_questoes** – simulado_id, questao_id, ordem (tabela pivot)
