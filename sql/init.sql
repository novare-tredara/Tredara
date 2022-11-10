-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: tredara
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auction_item`
--

DROP TABLE IF EXISTS `auction_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auction_item` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `IMAGE` varchar(255) DEFAULT NULL,
  `ORIGINAL_PRICE` bigint DEFAULT NULL,
  `SOLD_PRICE` bigint DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CATEGORY_ID` int DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  `STATUS` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ITEM_CATEGORY_ID` (`CATEGORY_ID`),
  KEY `ITEM_USER_ID` (`USER_ID`),
  CONSTRAINT `ITEM_CATEGORY_ID` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `category` (`ID`),
  CONSTRAINT `ITEM_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_item`
--

LOCK TABLES `auction_item` WRITE;
/*!40000 ALTER TABLE `auction_item` DISABLE KEYS */;
INSERT INTO `auction_item` VALUES (7,'Ear ring  nice for any occassions','2022-11-20 00:00:00','/images/accessories/elegantaearring.png',200,NULL,'2022-11-01 00:00:00','Ear piece',2,8,1),(9,'A nice Red lipstick for women who try to look bright and beautiful on special occasions','2022-11-20 00:00:00','/images/beautycare\\1667945355215.png',200,NULL,'2022-11-01 00:00:00','Lip Stick / U',5,8,1),(11,'A book for night time sleep','2022-11-20 00:00:00','/images/books/book.png',20,NULL,'2022-11-02 00:00:00','Misel Rosen book ',4,4,1),(12,'Some random books which I want  to give away','2022-11-29 00:00:00','/images/books/book3.png',50,NULL,'2022-11-11 00:00:00','Random Books',4,4,1),(13,'A nexus phone with good battery life and good memeory ','2022-11-20 00:00:00','/images/mobiles\\1667984844855.png',600,NULL,'2022-11-10 00:00:00','Nexus Phone-update',1,6,1),(14,'A Nokia phone with good pixel quality','2022-11-12 00:00:00','/images/mobiles/Nokia.png',100,NULL,'2022-11-02 00:00:00','A Nokia Phone',1,7,1),(44,'Brand New','2022-12-31 01:00:00','/images/mobiles\\1667942678144.png',3000,NULL,'2022-11-04 00:00:00','Iphone',1,8,1),(47,'Brand New','2022-12-31 00:00:00','/images/books\\1667948979884.png',2000,NULL,'2022-11-09 00:00:00','Java Book-Update',4,6,1),(48,'Brand New','2022-11-10 00:00:00','/images/beautycare\\1667978231656.png',100,NULL,'2022-11-09 00:00:00','Powder',5,8,1),(51,'Ear ring  nice for any occassions','2022-11-20 00:00:00','/images/accessories\\1667998889656.png',215,NULL,'2022-11-01 00:00:00','Ear piece- new one',2,8,1),(52,'Ear ring  nice for any occassions','2022-11-20 00:00:00','/images/accessories\\1667998933109.png',200,NULL,'2022-11-01 00:00:00','Ear piece',2,8,1),(53,'Brand New','2022-11-10 00:00:00','/images/books\\1668001219745.png',130,NULL,'2022-11-09 00:00:00','book',4,8,1);
/*!40000 ALTER TABLE `auction_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bidding_history`
--

DROP TABLE IF EXISTS `bidding_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bidding_history` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `BIDDING_PRICE` bigint DEFAULT NULL,
  `CREATED_ON` datetime DEFAULT NULL,
  `AUCTION_ID` int NOT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `BID_AUCTION_ID` (`AUCTION_ID`),
  KEY `ITEM_BIDDER_ID` (`USER_ID`),
  CONSTRAINT `BID_AUCTION_ID` FOREIGN KEY (`AUCTION_ID`) REFERENCES `auction_item` (`ID`),
  CONSTRAINT `ITEM_BIDDER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidding_history`
--

LOCK TABLES `bidding_history` WRITE;
/*!40000 ALTER TABLE `bidding_history` DISABLE KEYS */;
INSERT INTO `bidding_history` VALUES (1,NULL,'2022-11-09 11:18:00',12,6),(2,989898,'2022-11-09 11:26:01',12,6),(3,32423,'2022-11-09 11:30:29',12,6),(4,32423,'2022-11-09 11:32:48',12,6),(5,234234,'2022-11-09 11:34:04',12,6),(6,23,'2022-11-10 12:32:02',12,6),(7,1200000,'2022-11-10 02:00:48',12,8),(9,200,'2022-11-10 07:15:27',14,8),(10,205,'2022-11-10 09:35:12',14,8),(11,300,'2022-11-10 09:46:23',14,8),(12,30,'2022-11-10 09:50:00',11,8),(13,140,'2022-11-10 11:40:54',53,6);
/*!40000 ALTER TABLE `bidding_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CATEGORY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'MOBILES'),(2,'ACCESSORIES'),(3,'CLOTHES'),(4,'BOOKS'),(5,'BEAUTYCARE');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ROLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_CUSTOMER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATED_ON` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FULL_NAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` int DEFAULT NULL,
  `ROLE_ID` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `USER_ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `USER_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (4,'2022-11-07 12:05:26','john@tredara.com','John','$2a$10$pFwz.BYCFlSBlpDNcPaTfepPMfAP.ZY0PnD2dyMox/ede6b.Qsw9G',1,2),(5,'2022-11-07 12:18:37','admin@tredara.com','Admin','$2a$10$9WAqpJC5B2R/u9TxPvRC6ONb5CNF8I/xrc3XXeq.Fh.ZmVh96Gvy2',1,1),(6,'2022-11-07 12:22:15','radha@tredara.com','Radha','$2a$10$/0e2qawATB.flpdGwBpq/O6K6no31HUD1vhe8wzSVB2MOOB8kXOie',1,2),(7,'2022-11-07 12:23:04','latha@tredara.com','latha','$2a$10$sLK9ZUBgeAnReJmIWHYyoOmG2ZE4v0DgVw3AmDH6JpvC0bqxkaYlC',1,2),(8,'2022-11-07 12:23:59','mallika@tredara.com','Mallika','$2a$10$Nk/KHCIf3/1/3s0iUqJoK.BACjEIzi3uuSxfUxq5TaTXo.as1ruwC',1,2),(10,'2022-11-10 11:09:08','kumar@tredara.com','kumar','$2a$10$diuWY5TOkss/nn47InY0zuDiFxqOm7T9y7Oc7mI282FI3oHb5WHJG',1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 13:23:54
