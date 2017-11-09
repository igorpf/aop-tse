INSERT INTO autor (nome) VALUES ('Criptônio');
INSERT INTO autor (nome) VALUES ('Radônio');
INSERT INTO autor (nome) VALUES ('Einstênio');

INSERT INTO gincana (nome) VALUES ('Gincana Municipal de Capela de Santana');
INSERT INTO gincana (nome) VALUES ('Gincana Municipal de Bom Princípio');

INSERT INTO categoria (nome) VALUES ('Charada');
INSERT INTO categoria (nome) VALUES ('Rua');
INSERT INTO categoria (nome) VALUES ('Esportivas');
INSERT INTO categoria (nome) VALUES ('Diversas');

INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (1, 'Eai', TIMESTAMP '2016-03-23 15:00:00','Tarefa Numero um', '300 pontos', true, 1, 1, 1);
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (2, 'Entreguem o que estamos pedindo', TIMESTAMP '2016-03-23 14:00:00','Tarefa Numero dois', '200 pontos', true, 3, 2, 1);
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (3, 'Entreguem o que estamos pedindo', TIMESTAMP '2016-03-23 14:00:00','Tarefa Numero tres', '300 pontos', true, 3, 2, 1);
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (7, 'Entreguem o que queremos', TIMESTAMP '2016-04-23 14:00:00','Tarefa Numero Sete', '700 pontos', false, 2, 1, 2);			
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (10, 'asas', TIMESTAMP '2016-04-23 15:00:00','Tarefa Numero Dez', '500 pontos', false, 1, 1, 2);			
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (11, 'Sasdas', TIMESTAMP '2016-03-23 15:01:00','Tarefa Numero Onze', '100 pontos', true, 2, 2, 2);			
INSERT INTO tarefa (id, corpo, datapostagem, nome, pontuacao, visivel, autor_id, categoria_id, gincana_id)
			VALUES (50, 'Sasdas', TIMESTAMP '2016-04-23 16:05:00','Tarefa Numero Onze', '100 pontos', false, 3, 3, 2);			
