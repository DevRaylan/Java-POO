CREATE DATABASE IF NOT EXISTS db_lavacao3_5;
USE db_lavacao3_5;

-- Create table for vehicle marca (brand)
CREATE TABLE marca (
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   PRIMARY KEY (id)
);

-- Create table for vehicle cor (color)
CREATE TABLE cor (
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   PRIMARY KEY (id)
);

-- Create table for vehicle modelo (model)
CREATE TABLE modelo (
   id INT NOT NULL AUTO_INCREMENT,
   descricao VARCHAR(50) NOT NULL,
   categoria ENUM('PEQUENO', 'MEDIO', 'GRANDE', 'MOTO', 'PADRAO') NOT NULL,
   id_marca INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_marca) REFERENCES marca (id)
);

/*TABELA MOTOR COM RELACIONAMENTO 1:1 PARA PRODUTO*/
CREATE TABLE motor(
	id_modelo INT NOT NULL REFERENCES modelo(id),
    potencia INT not null,
    tipo_combustivel ENUM('GASOLINA', 'ETANOL', 'FLEX', 'DISEL', 'GNV', 'OUTROS') NOT NULL,
    CONSTRAINT pk_motor PRIMARY KEY (id_modelo),
    CONSTRAINT fk_motor_modelo FOREIGN KEY (id_modelo) REFERENCES modelo(id) ON DELETE CASCADE
);

-- Create table for vehicle veiculo (vehicle)
CREATE TABLE veiculo (
   id INT NOT NULL AUTO_INCREMENT,
   placa VARCHAR(50) NOT NULL,
   observacoes VARCHAR(255) NOT NULL, -- Observacoes com 'o' minúsculo
   id_modelo INT NOT NULL,
   id_cor INT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (id_modelo) REFERENCES modelo (id),
   FOREIGN KEY (id_cor) REFERENCES cor (id) -- Adicionado chave estrangeira para cor
);

-- Insert sample data for vehicle marcas (brands)
INSERT INTO marca (nome) VALUES ('Toyota');
INSERT INTO marca (nome) VALUES ('Honda');
INSERT INTO marca (nome) VALUES ('Ford');

-- Insert sample data for vehicle cores (colors)
INSERT INTO cor (nome) VALUES ('Preto');
INSERT INTO cor (nome) VALUES ('Branco');
INSERT INTO cor (nome) VALUES ('Prata');

-- Insert sample data for vehicle modelos (models)
INSERT INTO modelo (descricao, id_marca) VALUES ('Tcross', 1); -- Assuming 'Toyota' has id 1
INSERT INTO modelo (descricao, id_marca) VALUES ('Civic', 2);  -- Assuming 'Honda' has id 2
INSERT INTO modelo (descricao, id_marca) VALUES ('Fox', 3);    -- Assuming 'Ford' has id 3

-- Insert sample data for vehicle veiculos (veiculs)
INSERT INTO veiculo (placa, observacoes, id_modelo, id_cor) VALUES ('ABC123', 'Ótimo estado', 1, 1); -- Veículo Toyota Tcross, Preto
INSERT INTO veiculo (placa, observacoes, id_modelo, id_cor) VALUES ('XYZ789', 'Pouco uso', 2, 2);   -- Veículo Honda Civic, Branco
INSERT INTO veiculo (placa, observacoes, id_modelo, id_cor) VALUES ('DEF456', 'Excelente condição', 3, 3); -- Veículo Ford Fox, Prata


INSERT INTO motor (id_modelo, potencia, tipo_combustivel) VALUES (1, 150, 'FLEX'); -- Modelo 'Tcross' tem id 1
INSERT INTO motor (id_modelo, potencia, tipo_combustivel) VALUES (2, 140, 'GASOLINA'); -- Modelo 'Civic' tem id 2
INSERT INTO motor (id_modelo, potencia, tipo_combustivel) VALUES (3, 120, 'FLEX'); -- Modelo 'Fox' tem id 3
