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
(1, 'Múltipla Escolha',  CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Certo ou Errado',   CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ============================================================
-- QUESTÕES (50 questões distribuídas entre as 8 matérias)
-- ============================================================

-- ---- PORTUGUÊS (materia 1) - 7 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(1,
 'No trecho "A despeito das críticas, o projeto foi aprovado", a expressão "a despeito de" pode ser substituída, sem alteração de sentido, por:',
 'Em virtude de', 'Apesar de', 'Por causa de', 'Em razão de', 'Graças a',
 'B', 1, 1, 1, 1, 1, 1, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(2,
 'Assinale a alternativa em que a crase está empregada CORRETAMENTE:',
 'Ele foi à pé até o trabalho.', 'Entregou o documento à secretária.', 'Fez referência à este artigo.', 'Chegou à casa cansado.', 'Saiu à procura à um táxi.',
 'B', 1, 1, 3, 3, 2, 2, 2, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(3,
 'Em "Os alunos e o professor chegou atrasado", há erro de:',
 'Regência verbal', 'Concordância nominal', 'Concordância verbal', 'Colocação pronominal', 'Pontuação',
 'C', 1, 1, 4, 4, 3, 1, 3, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(4,
 'Sobre a redação oficial, o formato padrão de um ofício deve conter, obrigatoriamente:',
 'Vocativo, texto e fecho', 'Apenas o texto e a assinatura', 'Cabeçalho com brasão e texto livre', 'Saudação informal e corpo', 'Título e subtítulo',
 'A', 1, 1, 5, 5, 4, 5, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(5,
 'Na frase "Não me disseram onde estavam os documentos", a palavra "onde" classifica-se como:',
 'Conjunção integrante', 'Advérbio interrogativo', 'Pronome relativo', 'Preposição', 'Conjunção coordenativa',
 'B', 1, 1, 2, 2, 5, 3, 5, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(6,
 'Assinale a alternativa que apresenta INCORREÇÃO quanto à concordância verbal:',
 'Fazem cinco anos que não o vejo.', 'Havia muitos candidatos na sala.', 'Devem existir razões para isso.', 'Faz dias quentes no Nordeste.', 'Houve vários acidentes na estrada.',
 'A', 1, 1, 4, 4, 1, 1, 1, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(7,
 'O texto dissertativo-argumentativo exige, em sua estrutura:',
 'Narração de fatos em ordem cronológica', 'Tese, argumentos e conclusão', 'Descrição detalhada de personagens', 'Diálogos entre interlocutores', 'Sequência temporal de eventos',
 'B', 1, 1, 1, 1, 2, 2, 2, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- DIREITO CONSTITUCIONAL (materia 2) - 7 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(8,
 'Segundo o art. 5º da CF/88, qual dos seguintes direitos é inviolável?',
 'Direito à propriedade sem função social', 'Liberdade de locomoção em tempo de guerra', 'Intimidade, vida privada, honra e imagem', 'Direito de reunião armada', 'Sigilo de correspondência sem exceções',
 'C', 1, 2, 6, 6, 1, 1, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(9,
 'A organização político-administrativa da República Federativa do Brasil compreende:',
 'A União, os Estados e os Municípios', 'A União, os Estados, o Distrito Federal e os Municípios', 'A União e os Estados apenas', 'A União, os Estados e os Territórios', 'A União e os Municípios',
 'B', 1, 2, 7, 7, 2, 1, 2, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(10,
 'O processo legislativo ordinário compreende, em regra:',
 'Apenas votação em turno único', 'Discussão e votação em cada Casa do Congresso', 'Aprovação exclusiva pelo Senado', 'Iniciativa exclusiva do Presidente', 'Votação apenas na Câmara dos Deputados',
 'B', 1, 2, 8, 8, 3, 3, 3, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(11,
 'É competência privativa do Presidente da República:',
 'Julgar as contas do Governo', 'Sancionar, promulgar e fazer publicar as leis', 'Aprovar emendas constitucionais', 'Declarar a inconstitucionalidade de lei', 'Fiscalizar o orçamento público',
 'B', 1, 2, 9, 9, 4, 5, 4, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(12,
 'A Ação Direta de Inconstitucionalidade (ADI) pode ser proposta por:',
 'Qualquer cidadão brasileiro', 'O Presidente da República e outros legitimados do art. 103', 'Apenas o Procurador-Geral da República', 'Qualquer juiz de primeiro grau', 'O Presidente do Senado exclusivamente',
 'B', 1, 2, 10, 10, 5, 1, 5, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(13,
 'Os direitos sociais previstos no art. 6º da CF/88 incluem:',
 'Educação, saúde, alimentação e moradia', 'Apenas educação e saúde', 'Liberdade de expressão e reunião', 'Direito ao voto e à candidatura', 'Propriedade e herança',
 'A', 1, 2, 6, 6, 1, 2, 1, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(14,
 'É competência exclusiva da União legislar sobre:',
 'Direito tributário', 'Direito civil, comercial, penal e processual', 'Proteção ao meio ambiente', 'Educação e cultura', 'Assistência jurídica e defensoria pública',
 'B', 1, 2, 7, 7, 2, 3, 2, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- DIREITO TRIBUTÁRIO (materia 3) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(15,
 'O Sistema Tributário Nacional é regido por normas previstas:',
 'Apenas em leis ordinárias', 'Na Constituição Federal e no Código Tributário Nacional', 'Exclusivamente em decretos', 'Em portarias do Ministério da Fazenda', 'Apenas em leis complementares',
 'B', 1, 3, 11, 11, 1, 1, 1, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(16,
 'O Imposto de Renda é de competência:',
 'Dos Estados', 'Da União', 'Dos Municípios', 'Do Distrito Federal exclusivamente', 'Compartilhada entre União e Estados',
 'B', 1, 3, 12, 12, 1, 1, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(17,
 'O fato gerador da obrigação tributária principal é:',
 'A situação definida em lei como necessária e suficiente à sua ocorrência', 'Qualquer ato praticado pelo contribuinte', 'A inscrição no cadastro fiscal', 'O pagamento do tributo', 'A notificação do lançamento',
 'A', 1, 3, 13, 13, 2, 3, 2, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(18,
 'São modalidades de extinção do crédito tributário, EXCETO:',
 'Pagamento', 'Compensação', 'Moratória', 'Prescrição', 'Remissão',
 'C', 1, 3, 14, 14, 1, 1, 1, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(19,
 'A imunidade recíproca prevista no art. 150, VI, "a" da CF/88 veda:',
 'A cobrança de taxas entre os entes', 'A instituição de impostos sobre patrimônio, renda ou serviços entre os entes federativos', 'A cobrança de contribuições de melhoria', 'A tributação de empresas estatais', 'A instituição de empréstimos compulsórios',
 'B', 1, 3, 15, 15, 5, 1, 5, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(20,
 'O lançamento tributário por declaração ocorre quando:',
 'O Fisco realiza o lançamento sem participação do contribuinte', 'O contribuinte presta informações e o Fisco calcula o tributo', 'O contribuinte calcula e paga antecipadamente', 'O juiz determina o valor do tributo', 'O tributo é cobrado na fonte',
 'B', 1, 3, 13, 13, 1, 2, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- ESTATÍSTICA (materia 4) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(21,
 'A média aritmética dos valores 4, 8, 6, 10 e 12 é:',
 '6', '7', '8', '9', '10',
 'C', 1, 4, 16, 16, 4, 5, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(22,
 'Se P(A) = 0,3 e P(B|A) = 0,5, então P(A ∩ B) é igual a:',
 '0,8', '0,2', '0,15', '0,35', '0,10',
 'C', 1, 4, 17, 17, 2, 3, 2, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(23,
 'Na distribuição normal padrão, a média e o desvio padrão são, respectivamente:',
 '1 e 0', '0 e 1', '0 e 0', '1 e 1', '0,5 e 0,5',
 'B', 1, 4, 18, 18, 3, 1, 3, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(24,
 'Na amostragem estratificada, a população é dividida em:',
 'Conglomerados aleatórios', 'Grupos homogêneos chamados estratos', 'Amostras sistemáticas', 'Subconjuntos de tamanho fixo', 'Parcelas proporcionais ao erro',
 'B', 1, 4, 19, 19, 5, 3, 5, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(25,
 'Em um teste de hipótese, o erro tipo I ocorre quando:',
 'Aceitamos H0 sendo H0 verdadeira', 'Rejeitamos H0 sendo H0 verdadeira', 'Aceitamos H1 sendo H1 verdadeira', 'Rejeitamos H1 sendo H1 falsa', 'Aceitamos H0 sendo H0 falsa',
 'B', 1, 4, 20, 20, 1, 1, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(26,
 'A mediana do conjunto {3, 7, 8, 5, 12, 1, 9} é:',
 '5', '6', '7', '8', '9',
 'C', 1, 4, 16, 16, 4, 2, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- RACIOCÍNIO LÓGICO (materia 5) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(27,
 'A negação da proposição "Se chove, então a rua fica molhada" é:',
 'Se não chove, então a rua não fica molhada', 'Chove e a rua não fica molhada', 'Não chove ou a rua fica molhada', 'Se a rua fica molhada, então chove', 'Não chove e a rua fica molhada',
 'B', 1, 5, 21, 21, 1, 1, 1, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(28,
 'O valor lógico da proposição composta "V ∧ F → V" é:',
 'Verdadeiro', 'Falso', 'Indeterminado', 'Depende do contexto', 'Nenhuma das alternativas',
 'A', 1, 5, 22, 22, 3, 1, 3, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(29,
 'A contrapositiva de "Se estudo, então passo" é:',
 'Se passo, então estudo', 'Se não estudo, então não passo', 'Se não passo, então não estudo', 'Estudo ou não passo', 'Passo e não estudo',
 'C', 1, 5, 23, 23, 2, 3, 2, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(30,
 'Na sequência 2, 6, 18, 54, ..., o próximo termo é:',
 '108', '128', '162', '180', '216',
 'C', 1, 5, 24, 24, 4, 5, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(31,
 'De quantas maneiras diferentes 5 pessoas podem se sentar em 5 cadeiras?',
 '25', '60', '100', '120', '150',
 'D', 1, 5, 25, 25, 5, 1, 5, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(32,
 'Se "Todo auditor é concursado" é verdadeira, então é NECESSARIAMENTE verdadeira:',
 'Todo concursado é auditor', 'Algum concursado é auditor', 'Nenhum concursado é auditor', 'Algum auditor não é concursado', 'Nenhum auditor é concursado',
 'B', 1, 5, 21, 21, 1, 2, 1, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- DIREITO ADMINISTRATIVO (materia 6) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(33,
 'São atributos do ato administrativo:',
 'Legalidade, moralidade e publicidade', 'Presunção de legitimidade, imperatividade, autoexecutoriedade e tipicidade', 'Eficiência, eficácia e efetividade', 'Hierarquia, disciplina e legalidade', 'Continuidade, razoabilidade e motivação',
 'B', 1, 6, 26, 26, 2, 1, 2, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(34,
 'Segundo a Lei nº 14.133/2021, a modalidade de licitação obrigatória para obras acima do valor estabelecido é:',
 'Pregão', 'Concorrência', 'Tomada de preços', 'Convite', 'Leilão',
 'B', 1, 6, 27, 27, 5, 3, 5, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(35,
 'O regime jurídico dos servidores públicos civis da União é disciplinado pela:',
 'CLT', 'Lei nº 8.112/1990', 'Lei nº 8.666/1993', 'Constituição Federal apenas', 'Lei nº 9.784/1999',
 'B', 1, 6, 28, 28, 3, 1, 3, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(36,
 'O poder de polícia é a atividade do Estado que:',
 'Investiga crimes e contravenções', 'Limita direitos individuais em prol do interesse público', 'Aplica sanções penais aos infratores', 'Garante a ordem pública por meio das forças armadas', 'Regulamenta apenas o trânsito',
 'B', 1, 6, 29, 29, 4, 2, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(37,
 'A responsabilidade civil do Estado, conforme o art. 37, §6º da CF/88, é:',
 'Subjetiva em todos os casos', 'Objetiva, baseada na teoria do risco administrativo', 'Inexistente para atos lícitos', 'Solidária com o agente público', 'Exclusivamente contratual',
 'B', 1, 6, 30, 30, 1, 1, 1, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(38,
 'A anulação de um ato administrativo ilegal produz efeitos:',
 'Ex nunc, ou seja, apenas para o futuro', 'Ex tunc, retroagindo à data de origem do ato', 'Apenas após trânsito em julgado', 'Somente se houver decisão judicial', 'A partir da publicação no Diário Oficial',
 'B', 1, 6, 26, 26, 2, 3, 2, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- DIREITOS HUMANOS (materia 7) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(39,
 'A Declaração Universal dos Direitos Humanos foi adotada pela ONU em:',
 '1945', '1948', '1950', '1966', '1969',
 'B', 1, 7, 31, 31, 3, 1, 3, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(40,
 'A Corte Interamericana de Direitos Humanos tem sede em:',
 'Washington, EUA', 'Genebra, Suíça', 'San José, Costa Rica', 'Brasília, Brasil', 'Buenos Aires, Argentina',
 'C', 1, 7, 32, 32, 3, 1, 3, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(41,
 'O direito à liberdade de expressão, segundo o Pacto Internacional dos Direitos Civis e Políticos:',
 'É absoluto e não admite restrições', 'Pode ser restringido para proteger a segurança nacional ou a ordem pública', 'Aplica-se apenas a cidadãos nacionais', 'Não inclui a liberdade de imprensa', 'Foi revogado em 2010',
 'B', 1, 7, 33, 33, 5, 3, 5, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(42,
 'O Pacto Internacional dos Direitos Econômicos, Sociais e Culturais garante, entre outros, o direito:',
 'Ao porte de armas', 'À educação e ao trabalho em condições justas', 'À liberdade de locomoção irrestrita', 'Ao voto universal e secreto', 'À propriedade privada sem limites',
 'B', 1, 7, 34, 34, 4, 5, 4, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(43,
 'A Convenção Americana sobre Direitos Humanos (Pacto de São José da Costa Rica) foi adotada em:',
 '1948', '1966', '1969', '1988', '1992',
 'C', 1, 7, 35, 35, 3, 1, 3, 2022, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(44,
 'Segundo a DUDH, todo ser humano tem direito à vida, à liberdade e à:',
 'Propriedade', 'Felicidade', 'Segurança pessoal', 'Nacionalidade', 'Educação superior',
 'C', 1, 7, 31, 31, 1, 2, 1, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ---- INFORMÁTICA (materia 8) - 6 questões ----

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(45,
 'No sistema operacional Linux, o comando utilizado para listar arquivos e diretórios é:',
 'dir', 'ls', 'list', 'show', 'cat',
 'B', 1, 8, 36, 36, 3, 1, 3, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(46,
 'O protocolo responsável pela transferência de páginas web é o:',
 'FTP', 'SMTP', 'HTTP', 'POP3', 'DNS',
 'C', 1, 8, 37, 37, 4, 5, 4, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(47,
 'A criptografia assimétrica utiliza:',
 'Uma única chave para cifrar e decifrar', 'Um par de chaves: pública e privada', 'Apenas certificados digitais', 'Senhas de acesso ao sistema', 'Tokens físicos exclusivamente',
 'B', 1, 8, 38, 38, 1, 1, 1, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(48,
 'No Microsoft Excel, a fórmula para somar os valores de A1 a A10 é:',
 '=ADD(A1:A10)', '=SUM(A1:A10)', '=SOMA(A1:A10)', '=TOTAL(A1:A10)', '=COUNT(A1:A10)',
 'C', 1, 8, 39, 39, 2, 2, 2, 2024, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(49,
 'O modelo de computação em nuvem que oferece infraestrutura como serviço é conhecido como:',
 'SaaS', 'PaaS', 'IaaS', 'DaaS', 'FaaS',
 'C', 1, 8, 40, 40, 5, 3, 5, 2023, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

MERGE INTO questao (id, enunciado, alternativa_a, alternativa_b, alternativa_c, alternativa_d, alternativa_e, resposta_correta, tipo_id, materia_id, assunto_id, topico_id, orgao_id, banca_id, cargo_id, ano, created_at, updated_at) KEY(id) VALUES
(50,
 'Qual das alternativas representa um exemplo de malware que sequestra dados e exige pagamento para liberá-los?',
 'Worm', 'Trojan', 'Ransomware', 'Spyware', 'Adware',
 'C', 1, 8, 38, 38, 3, 1, 3, 2025, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
