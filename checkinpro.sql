-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: checkinpro
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sobrenome` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0000',
  `cpf` varchar(14) NOT NULL,
  `telefone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `data_cadastro` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (4,'Carlos','Siro','isossos@gmail.com','0000','48919071004','5465675765545','2025-05-26 19:27:59');
INSERT INTO `cliente` VALUES (6,'Samii','Zanin','samiizanin@gmail.com','0000','034.080.390-89','54999804026','2025-06-18 22:59:52');
INSERT INTO `cliente` VALUES (7,'Nicoli','Cieslak','nicki@gmail.com','0000','04720579019','545454545454','2025-06-20 21:27:09');
INSERT INTO `cliente` VALUES (10,'Neli','Zanin','neli@gmail.com','0000','92203000015','54999114023','2025-06-22 14:41:24');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_reserva` int NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  `metodo_pagamento` enum('Crédito','Débito','Dinheiro','PIX') NOT NULL,
  `status_pagamento` enum('Pendente','Concluído','Estornado') NOT NULL DEFAULT 'Pendente',
  `data_pagamento` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_reserva` (`id_reserva`),
  CONSTRAINT `pagamento_ibfk_1` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` VALUES (11,10,250.00,'Dinheiro','Concluído','2025-06-21 23:52:16');
INSERT INTO `pagamento` VALUES (18,11,2160.00,'Dinheiro','Concluído','2025-06-22 14:19:40');
INSERT INTO `pagamento` VALUES (20,12,14600.00,'Débito','Concluído','2025-06-25 21:31:11');
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quarto`
--

DROP TABLE IF EXISTS `quarto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quarto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo` enum('Standard','Deluxe','Suíte') NOT NULL,
  `descricao` text,
  `capacidade` int NOT NULL,
  `preco_diaria` decimal(10,2) NOT NULL,
  `status` enum('Disponível','Ocupado','Manutenção') NOT NULL DEFAULT 'Disponível',
  `nmr_quarto` int NOT NULL,
  `camas` int NOT NULL,
  `andar` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nmr_quarto` (`nmr_quarto`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quarto`
--

LOCK TABLES `quarto` WRITE;
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
INSERT INTO `quarto` VALUES (1,'Deluxe','Quarto aconchego para casais exigentes',2,250.00,'Disponível',1,1,1);
INSERT INTO `quarto` VALUES (2,'Standard','Quarto aconchegante para suruba entre 4 casais',4,350.00,'Disponível',2,2,2);
INSERT INTO `quarto` VALUES (3,'Suíte','Quarto elegante',2,1500.00,'Disponível',104,2,1);
INSERT INTO `quarto` VALUES (4,'Deluxe','Quarto casal com luxo garantido, ótimo custo benefício, frente a avenida brasil',2,450.00,'Disponível',22,1,2);
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int NOT NULL,
  `id_quarto` int NOT NULL,
  `data_checkin` date NOT NULL,
  `data_checkout` date NOT NULL,
  `quantidade_pessoas` int NOT NULL,
  `status` enum('Pendente','Confirmada','Cancelada','Concluída') NOT NULL DEFAULT 'Pendente',
  `data_reserva` datetime DEFAULT CURRENT_TIMESTAMP,
  `valor` decimal(10,2) NOT NULL,
  `max_hospedes` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_quarto` (`id_quarto`),
  CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_quarto`) REFERENCES `quarto` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
INSERT INTO `reserva` VALUES (10,6,1,'2025-06-25','2025-06-25',1,'Concluída','2025-06-19 01:20:40',250.00,NULL);
INSERT INTO `reserva` VALUES (11,7,3,'2026-05-29','2026-05-30',2,'Concluída','2025-06-20 09:30:10',1500.00,NULL);
INSERT INTO `reserva` VALUES (12,10,3,'2025-07-01','2025-07-10',1,'Concluída','2025-06-22 02:42:36',1500.00,NULL);
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva_servico`
--

DROP TABLE IF EXISTS `reserva_servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva_servico` (
  `id_servico` int NOT NULL,
  `quantidade` int NOT NULL DEFAULT '1',
  `reservas_id` int NOT NULL,
  PRIMARY KEY (`id_servico`,`reservas_id`),
  KEY `reservas_id` (`reservas_id`),
  CONSTRAINT `reserva_servico_ibfk_1` FOREIGN KEY (`id_servico`) REFERENCES `servicos_extra` (`id_servico`),
  CONSTRAINT `reserva_servico_ibfk_2` FOREIGN KEY (`reservas_id`) REFERENCES `reserva` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva_servico`
--

LOCK TABLES `reserva_servico` WRITE;
/*!40000 ALTER TABLE `reserva_servico` DISABLE KEYS */;
INSERT INTO `reserva_servico` VALUES (1,10,12);
INSERT INTO `reserva_servico` VALUES (2,2,11);
INSERT INTO `reserva_servico` VALUES (2,10,12);
INSERT INTO `reserva_servico` VALUES (3,2,11);
INSERT INTO `reserva_servico` VALUES (3,10,12);
INSERT INTO `reserva_servico` VALUES (4,2,11);
INSERT INTO `reserva_servico` VALUES (7,2,11);
INSERT INTO `reserva_servico` VALUES (8,1,11);
/*!40000 ALTER TABLE `reserva_servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicos_extra`
--

DROP TABLE IF EXISTS `servicos_extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicos_extra` (
  `id_servico` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicos_extra`
--

LOCK TABLES `servicos_extra` WRITE;
/*!40000 ALTER TABLE `servicos_extra` DISABLE KEYS */;
INSERT INTO `servicos_extra` VALUES (1,'Café',30.00);
INSERT INTO `servicos_extra` VALUES (2,'Almoço',50.00);
INSERT INTO `servicos_extra` VALUES (3,'Café da manhã',30.00);
INSERT INTO `servicos_extra` VALUES (4,'Serviço de quarto',40.00);
INSERT INTO `servicos_extra` VALUES (5,'Translado aeroporto',100.00);
INSERT INTO `servicos_extra` VALUES (6,'Lavanderia',25.00);
INSERT INTO `servicos_extra` VALUES (7,'Spa',150.00);
INSERT INTO `servicos_extra` VALUES (8,'Massagem relaxante',120.00);
INSERT INTO `servicos_extra` VALUES (9,'Banho de ofurô',80.00);
INSERT INTO `servicos_extra` VALUES (10,'Piscina aquecida',60.00);
INSERT INTO `servicos_extra` VALUES (11,'Cadeira de praia',15.00);
INSERT INTO `servicos_extra` VALUES (12,'Guarda-sol',10.00);
INSERT INTO `servicos_extra` VALUES (13,'Passeio turístico',90.00);
INSERT INTO `servicos_extra` VALUES (14,'Aluguel de bicicleta',20.00);
INSERT INTO `servicos_extra` VALUES (15,'Aluguel de carro',200.00);
INSERT INTO `servicos_extra` VALUES (16,'Babá',70.00);
INSERT INTO `servicos_extra` VALUES (17,'Pet care',50.00);
INSERT INTO `servicos_extra` VALUES (18,'Jantar romântico',180.00);
INSERT INTO `servicos_extra` VALUES (19,'Decoração especial',100.00);
INSERT INTO `servicos_extra` VALUES (20,'Cesta de café da manhã',85.00);
INSERT INTO `servicos_extra` VALUES (21,'Cesta de frutas',45.00);
INSERT INTO `servicos_extra` VALUES (22,'Mini bar',60.00);
INSERT INTO `servicos_extra` VALUES (23,'Wi-Fi premium',25.00);
INSERT INTO `servicos_extra` VALUES (28,'Treinador na academia',90.00);
INSERT INTO `servicos_extra` VALUES (29,'Curso de culinária',110.00);
INSERT INTO `servicos_extra` VALUES (30,'Aula de yoga',70.00);
INSERT INTO `servicos_extra` VALUES (33,'Concierge 24h',80.00);
INSERT INTO `servicos_extra` VALUES (34,'Espumante no quarto',95.00);
INSERT INTO `servicos_extra` VALUES (35,'Flores no quarto',55.00);
INSERT INTO `servicos_extra` VALUES (36,'Aromaterapia',65.00);
INSERT INTO `servicos_extra` VALUES (37,'Banheira de hidromassagem',150.00);
INSERT INTO `servicos_extra` VALUES (38,'Academia VIP',70.00);
INSERT INTO `servicos_extra` VALUES (39,'Kits de higiene pessoal',35.00);
INSERT INTO `servicos_extra` VALUES (40,'Lanches noturnos',40.00);
INSERT INTO `servicos_extra` VALUES (41,'Degustação de vinhos',160.00);
INSERT INTO `servicos_extra` VALUES (42,'Passeio de lancha',350.00);
INSERT INTO `servicos_extra` VALUES (43,'Aula de mergulho',300.00);
INSERT INTO `servicos_extra` VALUES (44,'Aula de dança',100.00);
INSERT INTO `servicos_extra` VALUES (45,'Tour gastronômico',220.00);
INSERT INTO `servicos_extra` VALUES (46,'Piquenique no parque',75.00);
INSERT INTO `servicos_extra` VALUES (47,'Cinema privativo',180.00);
INSERT INTO `servicos_extra` VALUES (48,'Jogos de tabuleiro',20.00);
INSERT INTO `servicos_extra` VALUES (49,'Sala de leitura',15.00);
INSERT INTO `servicos_extra` VALUES (50,'Karaokê',60.00);
INSERT INTO `servicos_extra` VALUES (51,'Bar na piscina',90.00);
INSERT INTO `servicos_extra` VALUES (52,'Aula de pintura',95.00);
INSERT INTO `servicos_extra` VALUES (53,'Aula de fotografia',140.00);
INSERT INTO `servicos_extra` VALUES (54,'Sala de jogos',55.00);
INSERT INTO `servicos_extra` VALUES (55,'Café colonial',65.00);
INSERT INTO `servicos_extra` VALUES (56,'Degustação de cervejas',120.00);
INSERT INTO `servicos_extra` VALUES (57,'Consultoria de moda',130.00);
INSERT INTO `servicos_extra` VALUES (58,'Barbearia',70.00);
INSERT INTO `servicos_extra` VALUES (59,'Manicure e pedicure',75.00);
INSERT INTO `servicos_extra` VALUES (60,'Aula de violão',85.00);
INSERT INTO `servicos_extra` VALUES (61,'Patinete Elétrico $/hora',50.00);
/*!40000 ALTER TABLE `servicos_extra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'checkinpro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-18 21:29:30
