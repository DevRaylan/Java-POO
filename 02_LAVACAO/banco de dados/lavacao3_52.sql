-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 04-Out-2023 às 23:47
-- Versão do servidor: 5.7.40
-- versão do PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_lavacao10`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `celular` varchar(50) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `celular`, `email`, `endereco`, `dataCadastro`) VALUES
(1, 'Edgar', '(11) 1111-1111', 'edgar@ifsc.edu.br', 'Rua Wenceslau Corrêa de Souza', '1996-08-17'),
(2, 'Marilene', '(22) 2222-2121', 'marilene@ifsc.edu.br', 'Quadra 1007 Sul Alameda 1 A', '2003-06-02'),
(3, 'Carla', '(33) 3333-3333', 'carla@ifsc.edu.br', 'Rua Santo Antônio', '1986-12-12'),
(4, 'José', '(44) 4444-4444', 'jose@ifsc.edu.br', 'Rua Isaura da Sílvia', '1968-03-07');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cor`
--

DROP TABLE IF EXISTS `cor`;
CREATE TABLE IF NOT EXISTS `cor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `cor`
--

INSERT INTO `cor` (`id`, `nome`) VALUES
(1, 'Preto'),
(2, 'Branco'),
(3, 'Azul'),
(4, 'Vermelho'),
(5, 'Cinza');

-- --------------------------------------------------------

--
-- Estrutura da tabela `item_os`
--

DROP TABLE IF EXISTS `item_os`;
CREATE TABLE IF NOT EXISTS `item_os` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valorServico` decimal(10,2) DEFAULT NULL,
  `observacoes` varchar(100) DEFAULT NULL,
  `id_servico` int(11) NOT NULL,
  `id_ordem_servico` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_item_OS_servico` (`id_servico`),
  KEY `fk_item_OS_ordem_servico` (`id_ordem_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `item_os`
--

INSERT INTO `item_os` (`id`, `valorServico`, `observacoes`, `id_servico`, `id_ordem_servico`) VALUES
(1, '75.50', 'Lavação Bicicleta', 1, 1),
(2, '115.25', 'Lavação Mobi', 2, 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `marca`
--

DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `marca`
--

INSERT INTO `marca` (`id`, `nome`) VALUES
(1, 'Chevrolet'),
(2, 'Fiat'),
(3, 'Toyota'),
(4, 'Foard');

-- --------------------------------------------------------

--
-- Estrutura da tabela `modelo`
--

DROP TABLE IF EXISTS `modelo`;
CREATE TABLE IF NOT EXISTS `modelo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `id_marca` int(11) NOT NULL,
  `categoria` enum('PEQUENO','MEDIO','GRANDE','MOTO','PADRAO') NOT NULL DEFAULT 'PADRAO',
  PRIMARY KEY (`id`),
  KEY `fk_modelo_marca` (`id_marca`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `modelo`
--

INSERT INTO `modelo` (`id`, `descricao`, `id_marca`, `categoria`) VALUES
(1, 'Equinox', 1, 'MEDIO'),
(2, 'Pulse', 2, 'MEDIO'),
(3, 'Corolla', 3, 'MEDIO'),
(4, 'Fusion', 4, 'MEDIO'),
(5, 'Corsa', 1, 'MEDIO');

-- --------------------------------------------------------

--
-- Estrutura da tabela `motor`
--

DROP TABLE IF EXISTS `motor`;
CREATE TABLE IF NOT EXISTS `motor` (
  `id_modelo` int(11) NOT NULL,
  `potencia` int(11) NOT NULL DEFAULT '100',
  `tipoCombustivel` enum('GASOLINA','ETANOL','FLEX','GNV','OUTRO') NOT NULL DEFAULT 'GASOLINA',
  PRIMARY KEY (`id_modelo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `motor`
--

INSERT INTO `motor` (`id_modelo`, `potencia`, `tipoCombustivel`) VALUES
(1, 172, 'GASOLINA'),
(2, 130, 'ETANOL'),
(3, 177, 'ETANOL'),
(4, 143, 'FLEX'),
(5, 112, 'GNV');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ordem_servico`
--

DROP TABLE IF EXISTS `ordem_servico`;
CREATE TABLE IF NOT EXISTS `ordem_servico` (
  `numero` int(11) NOT NULL AUTO_INCREMENT,
  `total` decimal(10,2) NOT NULL,
  `agenda` date NOT NULL,
  `desconto` double DEFAULT NULL,
  `id_veiculo` int(11) NOT NULL,
  `status` enum('ABERTA','FECHADA','CANCELADA') NOT NULL DEFAULT 'ABERTA',
  PRIMARY KEY (`numero`),
  KEY `fk_ordemServico_veiculo` (`id_veiculo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `ordem_servico`
--

INSERT INTO `ordem_servico` (`numero`, `total`, `agenda`, `desconto`, `id_veiculo`, `status`) VALUES
(1, '190.75', '2022-11-20', 0, 1, 'FECHADA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_fisica`
--

DROP TABLE IF EXISTS `pessoa_fisica`;
CREATE TABLE IF NOT EXISTS `pessoa_fisica` (
  `id_cliente` int(11) NOT NULL,
  `cpf` varchar(100) NOT NULL,
  `dataNascimento` date DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa_fisica`
--

INSERT INTO `pessoa_fisica` (`id_cliente`, `cpf`, `dataNascimento`) VALUES
(1, '111.111.111-11', '1970-04-20'),
(2, '222.222.222-22', '1979-10-18');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa_juridica`
--

DROP TABLE IF EXISTS `pessoa_juridica`;
CREATE TABLE IF NOT EXISTS `pessoa_juridica` (
  `id_cliente` int(11) NOT NULL,
  `cnpj` varchar(100) NOT NULL,
  `inscricaoEstadual` varchar(100) DEFAULT NULL,
  `dataNascimento` date DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa_juridica`
--

INSERT INTO `pessoa_juridica` (`id_cliente`, `cnpj`, `inscricaoEstadual`, `dataNascimento`) VALUES
(3, '11.719.809/0001-94', '040.240.270.481', '1977-02-01'),
(4, '11.719.809/0001-94', '720.324.234.299', '1975-08-27');

-- --------------------------------------------------------

--
-- Estrutura da tabela `servico`
--

DROP TABLE IF EXISTS `servico`;
CREATE TABLE IF NOT EXISTS `servico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `pontos` int(11) NOT NULL,
  `categoria` enum('PEQUENO','MEDIO','GRANDE','MOTO','PADRAO') NOT NULL DEFAULT 'PADRAO',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `servico`
--

INSERT INTO `servico` (`id`, `descricao`, `valor`, `pontos`, `categoria`) VALUES
(1, 'Lavação Pequena', 75.5, 20, 'PEQUENO'),
(2, 'Lavação Média', 115.25, 45, 'MEDIO'),
(3, 'Lavação Grande', 180, 75, 'GRANDE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `placa` varchar(100) NOT NULL,
  `observacoes` varchar(100) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `id_cor` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_veiculo_modelo` (`id_modelo`),
  KEY `fk_veiculo_cor` (`id_cor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `placa`, `observacoes`, `id_modelo`, `id_cor`, `id_cliente`) VALUES
(1, 'KDC-0230', 'Carro 2010', 4, 1, 1),
(2, 'NAJ-9579', 'Carro 2008', 5, 5, 2),
(3, 'JVV-4555', 'Carro 2018', 3, 4, 3),
(4, 'AZX-7081', 'Carro 2005', 1, 3, 4);

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `item_os`
--
ALTER TABLE `item_os`
  ADD CONSTRAINT `fk_item_OS_ordem_servico` FOREIGN KEY (`id_ordem_servico`) REFERENCES `ordem_servico` (`numero`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_item_OS_servico` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id`);

--
-- Limitadores para a tabela `modelo`
--
ALTER TABLE `modelo`
  ADD CONSTRAINT `fk_modelo_marca` FOREIGN KEY (`id_marca`) REFERENCES `marca` (`id`);

--
-- Limitadores para a tabela `motor`
--
ALTER TABLE `motor`
  ADD CONSTRAINT `fk_motor_modelo` FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id`) ON DELETE CASCADE;

--
-- Limitadores para a tabela `ordem_servico`
--
ALTER TABLE `ordem_servico`
  ADD CONSTRAINT `fk_ordemServico_veiculo` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id`);

--
-- Limitadores para a tabela `pessoa_fisica`
--
ALTER TABLE `pessoa_fisica`
  ADD CONSTRAINT `fk_pessoaFisica_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `pessoa_juridica`
--
ALTER TABLE `pessoa_juridica`
  ADD CONSTRAINT `fk_pessoaJuridica_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limitadores para a tabela `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `fk_veiculo_cor` FOREIGN KEY (`id_cor`) REFERENCES `cor` (`id`),
  ADD CONSTRAINT `fk_veiculo_modelo` FOREIGN KEY (`id_modelo`) REFERENCES `modelo` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
