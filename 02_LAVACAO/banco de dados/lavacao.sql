CREATE DATABASE IF NOT EXISTS db_lavacao;
USE db_lavacao;

-- Create table for vehicle marca (brand)
CREATE TABLE marca(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   CONSTRAINT pk_marca
  PRIMARY KEY(id)
);

-- Create table for vehicle cor (color)
CREATE TABLE cor(
   id int NOT NULL auto_increment,
   nome varchar(50) NOT NULL,
   CONSTRAINT pk_cor
  PRIMARY KEY(id)
);

-- Insert sample data for vehicle marcas (brands)
INSERT INTO marca(nome) VALUES('Toyota');
INSERT INTO marca(nome) VALUES('Honda');
INSERT INTO marca(nome) VALUES('Ford');

-- Insert sample data for vehicle cores (colors)
INSERT INTO cor(nome) VALUES('Preto');
INSERT INTO cor(nome) VALUES('Branco');
INSERT INTO cor(nome) VALUES('Prata');