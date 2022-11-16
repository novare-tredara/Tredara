-- MySQL dump 10.13  Distrib 8.0.30, for macos12 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_item`
--

LOCK TABLES `auction_item` WRITE;
/*!40000 ALTER TABLE `auction_item` DISABLE KEYS */;
INSERT INTO `auction_item` VALUES (1,'Glow cream that combines foundation, concealer, sunscreen and emollient. Using minerals that adapt to skin tone, Glow Time covers blemishes, minimizes pores and hides fine lines. Glow Time provides SPF 25.','2022-11-29 11:41:00','/images/beautycare/glowcream.png',120,NULL,'2022-11-16 11:00:00','Glow Cream',5,2,1),(2,'From the co-creator of How I Met Your Mother, a hilarious novel set in New York City. ','2022-11-21 11:00:00','/images/books/friendbook.png',50,NULL,'2022-11-15 12:00:00','The Mutual Friend',4,2,1),(3,'A nice hair clucth for a cascual occassions.','2022-12-01 12:00:00','/images/accessories/clip.png',20,NULL,'2022-11-12 12:00:00','Hair Clip',2,2,1),(4,'1+ mobile for smart new generation with nice front and back camera with 13 pixels','2022-12-12 12:00:00','/images/mobiles/1+.png',1000,NULL,'2022-11-10 12:00:00','1+ Mobile',1,3,1),(5,'A nice sweater with Cat design for coming Christmas celebration with cossy warm cuddly.','2023-01-01 12:00:00','/images/clothes/catswater.png',500,NULL,'2022-11-10 12:00:00','Cat design Sweater',3,3,1),(6,'Vintage clip-on sunglasses in good condition, with four clips, width lying 95mm and extended 110mm, lens diameter 38 x 40mm (irregular ovals).','2022-12-12 12:00:00','/images/accessories/sunglassess.png',200,NULL,'2022-11-09 12:00:00','Sunglassess',2,3,1),(7,'A nice mobiles cases with 3 different color matches your outfit','2022-12-10 12:00:00','/images/mobiles/mobilecase.png',200,NULL,'2022-11-15 12:00:00','Mobile cases',1,4,1),(8,'A nice shampoo to give shiny texture and mild fragrance , helps in hair growth','2022-11-29 12:00:00','/images/beautycare/shampoo.png',250,NULL,'2022-11-14 12:00:00','Shampoo',5,4,1),(9,'A funny funcky girly bracelt for your little girls to match with their outfit','2022-11-30 12:00:00','/images/accessories/bracelet.png',90,NULL,'2022-11-11 12:00:00','Bracelet',2,4,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidding_history`
--

LOCK TABLES `bidding_history` WRITE;
/*!40000 ALTER TABLE `bidding_history` DISABLE KEYS */;
INSERT INTO `bidding_history` VALUES (1,1050,'2022-11-11 02:20:20',4,2),(2,1200,'2022-11-12 06:30:30',4,4),(3,550,'2022-11-11 02:30:20',5,2),(4,300,'2022-11-10 11:00:00',6,4);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2022-11-07 12:18:37','admin@tredara.com','Admin','$2a$10$9WAqpJC5B2R/u9TxPvRC6ONb5CNF8I/xrc3XXeq.Fh.ZmVh96Gvy2',1,1),(2,'2022-11-07 12:23:04','sailathatammana1997@gmail.com','latha','$2a$10$aI/JOp1Tg0JN7wpXPQMh3Oz93CzZx6IJsSlLNhKZCOV0iBzZw2kZi',1,2),(3,'2022-11-16 11:30:20','mailtomallikaravi@gmail.com','mallika','$2a$10$aI/JOp1Tg0JN7wpXPQMh3Oz93CzZx6IJsSlLNhKZCOV0iBzZw2kZi',1,2),(4,'2022-11-16 11:36:44','radhacs71@gmail.com','radha','$2a$10$aI/JOp1Tg0JN7wpXPQMh3Oz93CzZx6IJsSlLNhKZCOV0iBzZw2kZi',1,2);
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

-- Dump completed on 2022-11-16 12:33:59
