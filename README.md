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
| GET | /api/questoes | Listar (filtros: tipoId, materiaId, assuntoId, topicoId, orgaoId, bancaId, cargoId, ano; paginação: `page`, `size`) |
| GET | /api/questoes/:id | Detalhes |
| POST | /api/questoes | Criar |
| PUT | /api/questoes/:id | Atualizar |
| DELETE | /api/questoes/:id | Deletar |

### Matérias
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/materias | Listar (paginação: `page`, `size`) |
| GET | /api/materias/:id | Detalhes |
| POST | /api/materias | Criar |
| PUT | /api/materias/:id | Atualizar |
| DELETE | /api/materias/:id | Deletar (query opcional: `cascade=true` para excluir em cascata assuntos, tópicos, questões vinculadas) |

### Assuntos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/assuntos | Listar (filtro opcional: `materiaId`; paginação: `page`, `size`) |
| GET | /api/assuntos/:id | Detalhes |
| POST | /api/assuntos | Criar |
| PUT | /api/assuntos/:id | Atualizar |
| DELETE | /api/assuntos/:id | Deletar |

### Tópicos
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/topicos | Listar (filtro opcional: `assuntoId`; paginação: `page`, `size`) |
| GET | /api/topicos/:id | Detalhes |
| POST | /api/topicos | Criar |
| PUT | /api/topicos/:id | Atualizar |
| DELETE | /api/topicos/:id | Deletar |

### Órgãos, Bancas, Cargos, Tipos
- **GET** /api/orgaos, /api/bancas, /api/cargos, /api/tipos – listar (com paginação)
- **GET** /api/.../:id – detalhes
- **POST** /api/... – criar
- **PUT** /api/.../:id – atualizar
- **DELETE** /api/.../:id – deletar

Filtro opcional: cargos por `orgaoId`.

### Simulados
| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /api/simulados | Listar (filtros: cargoId, orgaoId, ano) |
| GET | /api/simulados/:id | Detalhes (com questões) |
| POST | /api/simulados | Criar (incluir `simuladosQuestoes`: lista de `{ questaoId, ordem }`) |
| PUT | /api/simulados/:id | Atualizar |
| DELETE | /api/simulados/:id | Deletar |
| GET | /api/simulados/:id/gerar-docx | Download do simulado em DOCX |

### Dashboard e Export
- **GET** /api/dashboard/estatisticas – totais (questões, matérias, assuntos, tópicos, órgãos, bancas, cargos, simulados)
- **GET** /api/export/csv – exportar todos os dados em CSV (UTF-8)

## Exemplo de payloads

**Criar matéria** (POST /api/materias):
```json
{
  "nome": "Direito Constitucional"
}
```

**Atualizar matéria** (PUT /api/materias/:id):
```json
{
  "nome": "Direito Constitucional"
}
```

**Criar assunto** (POST /api/assuntos):
```json
{
  "nome": "Controle de Constitucionalidade",
  "materiaId": 1
}
```

**Atualizar assunto** (PUT /api/assuntos/:id):
```json
{
  "nome": "Controle de Constitucionalidade",
  "materiaId": 1
}
```

**Criar tópico** (POST /api/topicos):
```json
{
  "nome": "ADI e ADC",
  "assuntoId": 1
}
```

**Atualizar tópico** (PUT /api/topicos/:id):
```json
{
  "nome": "ADI e ADC",
  "assuntoId": 1
}
```

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
  "tipoId": 1,
  "materiaId": 1,
  "assuntoId": 1,
  "topicoId": 1,
  "orgaoId": 1,
  "bancaId": 1,
  "cargoId": 1,
  "ano": 2024
}
```

Campos opcionais na questão: `tipoId`, `assuntoId`, `topicoId`, `orgaoId`, `bancaId`, `cargoId`, `alternativaA` a `alternativaE`, `ano`. Obrigatórios: `enunciado`, `materiaId`.

**Atualizar questão** (PUT /api/questoes/:id): mesmo formato do criar.

**Criar simulado com questões** (POST /api/simulados):
```json
{
  "titulo": "Simulado Concurso 2024",
  "cargoId": 1,
  "orgaoId": 1,
  "ano": 2024,
  "ordemMaterias": [1, 2, 3],
  "simuladosQuestoes": [
    { "questaoId": 1, "ordem": 1 },
    { "questaoId": 2, "ordem": 2 }
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
