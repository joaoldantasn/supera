-- Inserindo dados na tabela tb_listatarefa
INSERT INTO tb_listatarefa (titulo) VALUES ('Lista de Compras');
INSERT INTO tb_listatarefa (titulo) VALUES ('Lista de Tarefas Domésticas');

-- Inserindo dados na tabela tb_tarefa associadas à tb_listatarefa
INSERT INTO tb_tarefa (titulo, descricao, data_conclusao, data_prevista, concluida, favorita, lista_tarefas_id) VALUES ('Comprar leite', 'Comprar 2 litros de leite', '2024-08-22', '2024-10-21', true, false, 1);

INSERT INTO tb_tarefa (titulo, descricao, data_conclusao, data_prevista, concluida, favorita, lista_tarefas_id) VALUES ('Comprar pão', 'Comprar pão integral', NULL, '2024-10-21', false, true, 1);

INSERT INTO tb_tarefa (titulo, descricao, data_conclusao, data_prevista, concluida, favorita, lista_tarefas_id) VALUES ('Lavar roupas', 'Lavar as roupas da semana', NULL, '2024-10-23', false, false, 2);

INSERT INTO tb_tarefa (titulo, descricao, data_conclusao, data_prevista, concluida, favorita, lista_tarefas_id) VALUES ('Limpar a cozinha', 'Limpar toda a cozinha, incluindo o fogão', NULL, '2024-10-22', false, true, 2);

-- Inserindo subtarefas associadas à tarefa com id 1
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Comprar pão integral', 1);
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Verificar validade do leite', 1);

-- Inserindo subtarefas associadas à tarefa com id 2
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Comprar pão francês', 2);

-- Inserindo subtarefas associadas à tarefa com id 3
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Separar roupas brancas e coloridas', 3);
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Lavar roupas delicadas separadas', 3);

-- Inserindo subtarefas associadas à tarefa com id 4
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Limpar fogão', 4);
INSERT INTO tb_subtarefa (nome, tarefa_id) VALUES ('Organizar armários', 4);
