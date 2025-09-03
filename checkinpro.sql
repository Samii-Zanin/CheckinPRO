create database checkinpro;
DROP TABLE IF EXISTS `cliente`;
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

DROP TABLE IF EXISTS `servicos_extra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicos_extra` (
  `id_servico` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





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

