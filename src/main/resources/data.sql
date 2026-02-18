-- ============================================================
-- Script de carga inicial para demonstrações
-- Executado automaticamente pelo Spring Boot (H2)
-- ============================================================

-- Só insere se a base estiver vazia (evita duplicação ao reiniciar)
MERGE INTO materia (id, nome, created_at, updated_at) KEY(id) VALUES
(1, 'Português', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Direito Constitucional', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Direito Tributário', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Estatística', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Raciocínio Lógico', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Direito Administrativo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Direitos Humanos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Informática', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- ASSUNTOS (5 por matéria = 40 assuntos)
-- ============================================================

-- Português (materia 1)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(1,  'Interpretação de Texto',          1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2,  'Gramática',                       1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3,  'Crase',                           1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4,  'Concordância Verbal e Nominal',   1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5,  'Redação Oficial',                 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Constitucional (materia 2)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(6,  'Direitos e Garantias Fundamentais',  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7,  'Organização do Estado',              2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8,  'Poder Legislativo',                  2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9,  'Poder Executivo',                    2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Controle de Constitucionalidade',    2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Tributário (materia 3)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(11, 'Sistema Tributário Nacional',     3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 'Impostos Federais',              3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 'Obrigação Tributária',           3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 'Crédito Tributário',             3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 'Limitações ao Poder de Tributar', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Estatística (materia 4)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(16, 'Estatística Descritiva',           4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17, 'Probabilidade',                    4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 'Distribuições de Probabilidade',   4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 'Amostragem',                       4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 'Inferência Estatística',           4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Raciocínio Lógico (materia 5)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(21, 'Proposições e Conectivos',   5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, 'Tabelas-Verdade',            5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 'Equivalências Lógicas',      5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, 'Raciocínio Sequencial',      5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(25, 'Análise Combinatória',       5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Administrativo (materia 6)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(26, 'Atos Administrativos',              6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(27, 'Licitações e Contratos',            6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(28, 'Servidores Públicos',               6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(29, 'Poderes da Administração',          6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(30, 'Responsabilidade Civil do Estado',  6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direitos Humanos (materia 7)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(31, 'Declaração Universal dos Direitos Humanos',  7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(32, 'Sistema Interamericano de Proteção',         7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(33, 'Direitos Civis e Políticos',                 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(34, 'Direitos Econômicos e Sociais',              7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(35, 'Tratados Internacionais',                    7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Informática (materia 8)
MERGE INTO assunto (id, nome, materia_id, created_at, updated_at) KEY(id) VALUES
(36, 'Sistemas Operacionais',       8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(37, 'Redes de Computadores',       8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(38, 'Segurança da Informação',     8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(39, 'Pacote Office',               8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(40, 'Computação em Nuvem',         8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- TÓPICOS (1 por assunto = 5 por matéria = 40 tópicos)
-- ============================================================

-- Português
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(1,  'Compreensão e Inferência Textual',       1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2,  'Classes de Palavras',                    2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3,  'Uso da Crase antes de Pronomes',         3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4,  'Sujeito Composto e Concordância',        4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5,  'Estrutura do Ofício e Memorando',        5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Constitucional
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(6,  'Art. 5º - Direitos Individuais',         6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7,  'Competências da União e Estados',        7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8,  'Processo Legislativo Ordinário',         8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9,  'Atribuições do Presidente',              9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'ADI, ADC e ADPF',                       10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Tributário
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(11, 'Competência Tributária',                  11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(12, 'Imposto de Renda',                       12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(13, 'Fato Gerador e Base de Cálculo',         13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(14, 'Suspensão e Extinção do Crédito',        14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(15, 'Imunidades Tributárias',                  15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Estatística
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(16, 'Medidas de Tendência Central',            16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(17, 'Probabilidade Condicional',               17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(18, 'Distribuição Normal',                     18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(19, 'Tipos de Amostragem',                     19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(20, 'Testes de Hipótese',                      20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Raciocínio Lógico
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(21, 'Negação de Proposições',                  21, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(22, 'Construção de Tabelas-Verdade',           22, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(23, 'Contrapositiva e Recíproca',              23, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(24, 'Sequências Numéricas',                    24, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(25, 'Permutações e Combinações',               25, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direito Administrativo
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(26, 'Atributos dos Atos Administrativos',      26, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(27, 'Modalidades de Licitação',                27, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(28, 'Regime Jurídico dos Servidores',          28, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(29, 'Poder de Polícia',                        29, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(30, 'Responsabilidade Objetiva do Estado',     30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Direitos Humanos
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(31, 'Artigos 1 a 30 da DUDH',                 31, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(32, 'Corte Interamericana de Direitos Humanos', 32, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(33, 'Liberdade de Expressão e Reunião',        33, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(34, 'Direito à Educação e Saúde',             34, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(35, 'Pacto de São José da Costa Rica',         35, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Informática
MERGE INTO topico (id, nome, assunto_id, created_at, updated_at) KEY(id) VALUES
(36, 'Windows e Linux',                         36, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(37, 'Protocolos TCP/IP e HTTP',                37, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(38, 'Criptografia e Certificação Digital',     38, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(39, 'Excel e Word Avançado',                   39, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(40, 'IaaS, PaaS e SaaS',                       40, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- ÓRGÃOS (5)
-- ============================================================
MERGE INTO orgao (id, nome, sigla, created_at, updated_at) KEY(id) VALUES
(1, 'Receita Federal do Brasil',           'RFB',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Tribunal de Contas da União',         'TCU',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Polícia Federal',                     'PF',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Instituto Nacional do Seguro Social', 'INSS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Controladoria-Geral da União',       'CGU',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- BANCAS (5)
-- ============================================================
MERGE INTO banca (id, nome, sigla, created_at, updated_at) KEY(id) VALUES
(1, 'CESPE/CEBRASPE',              'CESPE',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Fundação Carlos Chagas',      'FCC',    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Fundação Getúlio Vargas',     'FGV',    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Fundação VUNESP',             'VUNESP', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Instituto Brasileiro de Formação e Capacitação', 'IBFC', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- CARGOS (5 - vinculados aos órgãos)
-- ============================================================
MERGE INTO cargo (id, nome, orgao_id, created_at, updated_at) KEY(id) VALUES
(1, 'Auditor Fiscal',                          1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Auditor de Controle Externo',             2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Delegado de Polícia Federal',             3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Técnico do Seguro Social',               4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Auditor Federal de Finanças e Controle',  5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- TIPO DE QUESTÃO (para uso nas questões)
-- ============================================================
MERGE INTO tipo (id, nome, created_at, updated_at) KEY(id) VALUES
(1, 'Múltipla Escolha 4 alternativas',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Múltipla Escolha',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Certo ou Errado',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
