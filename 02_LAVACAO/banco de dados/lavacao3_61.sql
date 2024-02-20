CREATE DATABASE IF NOT EXISTS db_lavacao3_61;
USE db_lavacao3_61;

-- Create table for vehicle cor (color)
CREATE TABLE cor (
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   CONSTRAINT pk_cor
   PRIMARY KEY (id)
);
INSERT INTO cor (nome) VALUES
('Verde'),
('Amarelo'),
('Roxo'),
('Laranja'),
('Marrom'),
('Rosa'),
('Prata'),
('Dourado'),
('Bordo'),
('Bege');



-- Create table for vehicle marca (brand)
CREATE TABLE marca (
   id INT NOT NULL AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   CONSTRAINT pk_marca
   PRIMARY KEY (id)
);

INSERT INTO marca (nome) VALUES
('Chevrolet'),
('Fiat'),
('Toyota'),
('Ford'),
('Volkswagen'),
('Honda'),
('Hyundai'),
('Nissan'),
('BMW'),
('Audi'),
('Mercedes-Benz'),
('Kia'),
('Subaru'),
('Mazda');

-- Create table for vehicle modelo (model)
CREATE TABLE modelo (
   id INT NOT NULL AUTO_INCREMENT,
   descricao VARCHAR(50) NOT NULL,
   categoria ENUM('PEQUENO', 'MEDIO', 'GRANDE', 'MOTO', 'PADRAO') NOT NULL,
   id_marca INT NOT NULL,
   CONSTRAINT pk_modelo
      PRIMARY KEY(id),
   CONSTRAINT fk_modelo_marca
      FOREIGN KEY(id_marca)
      REFERENCES marca(id)
);

-- Add vehicle models for each brand
INSERT INTO modelo (descricao, categoria, id_marca) VALUES
('Sonic', 'PEQUENO', 1),          -- Chevrolet
('Camaro', 'GRANDE', 1),
('Cruze', 'MEDIO', 1),
('Onix', 'PEQUENO', 1),
('Tracker', 'PADRAO', 1),

('Mobi', 'PEQUENO', 2),           -- Fiat
('Argo', 'MEDIO', 2),
('Cronos', 'MEDIO', 2),
('Toro', 'PADRAO', 2),
('Uno', 'PEQUENO', 2),

('Yaris', 'MEDIO', 3),            -- Toyota
('Hilux', 'PADRAO', 3),
('Etios', 'PEQUENO', 3),
('RAV4', 'PADRAO', 3),
('Prius', 'PADRAO', 3),

('Focus', 'MEDIO', 4),            -- Ford
('Ranger', 'PADRAO', 4),
('Ka', 'PEQUENO', 4),
('EcoSport', 'PADRAO', 4),
('Fiesta', 'PEQUENO', 4),

('Golf', 'MEDIO', 5),             -- Volkswagen
('Passat', 'GRANDE', 5),
('T-Cross', 'PADRAO', 5),
('Jetta', 'MEDIO', 5),
('Up!', 'PEQUENO', 5),

('Civic', 'MEDIO', 6),            -- Honda
('City', 'PEQUENO', 6),
('HR-V', 'PADRAO', 6),
('Fit', 'PEQUENO', 6),
('Accord', 'GRANDE', 6),

('HB20', 'PEQUENO', 7),           -- Hyundai
('Creta', 'PADRAO', 7),
('i30', 'MEDIO', 7),
('Tucson', 'PADRAO', 7),
('Santa Fe', 'GRANDE', 7),

('Versa', 'MEDIO', 8),            -- Nissan
('March', 'PEQUENO', 8),
('Kicks', 'PADRAO', 8),
('Sentra', 'MEDIO', 8),
('Frontier', 'PADRAO', 8),

('Série 3', 'MEDIO', 9),          -- BMW
('X1', 'PADRAO', 9),
('Série 1', 'PEQUENO', 9),
('X3', 'PADRAO', 9),
('X5', 'GRANDE', 9),

('A3', 'MEDIO', 10),              -- Audi
('A4', 'MEDIO', 10),
('Q3', 'PADRAO', 10),
('A1', 'PEQUENO', 10),
('Q7', 'GRANDE', 10),

('Classe A', 'PEQUENO', 11),      -- Mercedes-Benz
('Classe C', 'MEDIO', 11),
('GLA', 'PADRAO', 11),
('Classe E', 'GRANDE', 11),
('Classe G', 'GRANDE', 11),

('Cerato', 'MEDIO', 12),          -- Kia
('Picanto', 'PEQUENO', 12),
('Sportage', 'PADRAO', 12),
('Sorento', 'GRANDE', 12),
('Rio', 'PEQUENO', 12),

('Impreza', 'MEDIO', 13),         -- Subaru
('Outback', 'PADRAO', 13),
('Forester', 'PADRAO', 13),
('Legacy', 'MEDIO', 13),
('XV', 'PADRAO', 13),

('MX-5', 'PEQUENO', 14),          -- Mazda
('CX-5', 'PADRAO', 14),
('Mazda2', 'PEQUENO', 14),
('Mazda3', 'MEDIO', 14),
('Mazda6', 'MEDIO', 14);


/*TABELA MOTOR COM RELACIONAMENTO 1:1 PARA PRODUTO*/
CREATE TABLE motor(
   id_modelo int NOT NULL,
   potencia int NOT NULL DEFAULT 100,
   tipoCombustivel enum('GASOLINA','ETANOL','FLEX','GNV','OUTRO') NOT NULL DEFAULT 'GASOLINA',
   CONSTRAINT pk_motor PRIMARY KEY (id_modelo),
    CONSTRAINT fk_motor_modelo FOREIGN KEY (id_modelo) REFERENCES modelo(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO motor (id_modelo, potencia, tipoCombustivel) VALUES
(1, 172, 'GASOLINA'),
(2, 130, 'ETANOL'),
(3, 177, 'ETANOL'),
(4, 143, 'FLEX'),
(5, 112, 'GNV'),
(6, 165, 'GASOLINA'),
(7, 125, 'ETANOL'),
(8, 150, 'FLEX'),
(9, 155, 'GASOLINA'),
(10, 120, 'ETANOL'),
(11, 130, 'GASOLINA'),
(12, 140, 'ETANOL'),
(13, 145, 'FLEX'),
(14, 135, 'GASOLINA'),
(15, 110, 'ETANOL'),
(16, 155, 'GASOLINA'),
(17, 130, 'ETANOL'),
(18, 145, 'FLEX'),
(19, 160, 'GASOLINA'),
(20, 120, 'ETANOL'),
(21, 140, 'GASOLINA'),
(22, 150, 'ETANOL'),
(23, 115, 'FLEX'),
(24, 135, 'GASOLINA'),
(25, 110, 'ETANOL'),
(26, 165, 'GASOLINA'),
(27, 125, 'ETANOL'),
(28, 140, 'FLEX'),
(29, 150, 'GASOLINA'),
(30, 130, 'ETANOL'),
(31, 145, 'GASOLINA'),
(32, 120, 'ETANOL'),
(33, 140, 'GASOLINA'),
(34, 150, 'ETANOL'),
(35, 115, 'FLEX'),
(36, 135, 'GASOLINA'),
(37, 110, 'ETANOL'),
(38, 155, 'GASOLINA'),
(39, 130, 'ETANOL'),
(40, 145, 'FLEX'),
(41, 160, 'GASOLINA'),
(42, 120, 'ETANOL'),
(43, 140, 'GASOLINA'),
(44, 150, 'ETANOL'),
(45, 115, 'FLEX'),
(46, 135, 'GASOLINA'),
(47, 110, 'ETANOL'),
(48, 155, 'GASOLINA'),
(49, 130, 'ETANOL'),
(50, 145, 'FLEX'),
(51, 160, 'GASOLINA'),
(52, 120, 'ETANOL'),
(53, 140, 'GASOLINA'),
(54, 150, 'ETANOL'),
(55, 115, 'FLEX'),
(56, 135, 'GASOLINA'),
(57, 110, 'ETANOL'),
(58, 155, 'GASOLINA'),
(59, 130, 'ETANOL'),
(60, 145, 'FLEX'),
(61, 160, 'GASOLINA'),
(62, 120, 'ETANOL'),
(63, 140, 'GASOLINA'),
(64, 150, 'ETANOL'),
(65, 115, 'FLEX'),
(66, 135, 'GASOLINA'),
(67, 110, 'ETANOL'),
(68, 155, 'GASOLINA'),
(69, 130, 'ETANOL'),
(70, 145, 'FLEX');

-- Create table for vehicle veiculo (vehicle)
CREATE TABLE veiculo (
   id INT NOT NULL AUTO_INCREMENT,
   placa VARCHAR(50) NOT NULL,
   observacoes VARCHAR(255) NOT NULL, -- Observacoes com 'o' minúsculo
   id_modelo INT NOT NULL,
   id_cor INT NOT NULL,
   CONSTRAINT pk_veiculo PRIMARY KEY (id),
   CONSTRAINT fk_veiculo_modelo FOREIGN KEY (id_modelo) REFERENCES modelo(id),
   CONSTRAINT fk_veiculo_cor FOREIGN KEY (id_cor) REFERENCES cor(id)
);



