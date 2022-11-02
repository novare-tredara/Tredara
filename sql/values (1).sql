Create Table tredara ;
Use tredara;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auction_item`
--

LOCK TABLES `auction_item` WRITE;
/*!40000 ALTER TABLE `auction_item` DISABLE KEYS */;
INSERT INTO `auction_item` VALUES (1,'A nice mobile which is in good condition','2022-11-07 00:00:00','/api/images/mobiles/Samsung.png',300,NULL,'2022-11-02 00:00:00','Samsung Mobile',1,1,1),(2,'A nice books for kids','2022-11-20 00:00:00','/api/images/books/kidsbook.png',100,NULL,'2022-11-02 00:00:00','Kids Book',4,2,1),(3,'A Cloth for men','2022-11-20 00:00:00','/api/images/clothes/checked.png',100,NULL,'2022-11-01 00:00:00','Men Shirt',3,3,1),(4,'Nice hair clips for girls','2022-11-21 00:00:00','/api/images/accesssories/hair-accessories.png',200,NULL,'2022-11-05 00:00:00','Hair Accessories',2,2,1),(5,'Beauty Cream ','2022-11-30 00:00:00','/api/images/beauty/cream.png',500,NULL,'2022-11-04 00:00:00','Face Cream',5,1,1),(6,'A nice cap for summer and stylish one','2022-11-12 00:00:00','/api/images/accessories/cap.png',50,NULL,'2022-11-02 00:00:00','Cap',2,3,1),(7,'Ear ring  nice for any occassions','2022-11-20 00:00:00','/api/images/accessories/elegantaearring.png',200,NULL,'2022-11-01 00:00:00','Ear piece',2,1,1),(8,'A hair clip for any occasssions with beautiful design ','2022-11-11 00:00:00','/api/images/accessories/hairclip.png',110,NULL,'2022-11-02 00:00:00','Hair Clip',2,1,1),(9,'A nice Red lipstick for women who try to look bright and beautiful on special occasions','2022-11-20 00:00:00','/api/images/beauty/lipstick.png',200,NULL,'2022-11-01 00:00:00','Lip Stick',5,3,1),(10,'A compact powder  from well branded company','2022-11-27 00:00:00','/api/images/beauty/compactpowder.png',500,NULL,'2022-11-05 00:00:00','Compact powder ',5,2,1),(11,'A book for night time sleep','2022-11-20 00:00:00','/api/images/books/book.png',20,NULL,'2022-11-02 00:00:00','Misel Rosen book ',4,2,1),(12,'Some random books which I want  to give away','2022-11-29 00:00:00','/api/images/books/book3.png',50,NULL,'2022-11-11 00:00:00','Random Books',4,1,1),(13,'A nexus phone with good battery life and good memeory ','2022-11-20 00:00:00','/api/imges/mobiles/Nexus.png',600,NULL,'2022-11-10 00:00:00','Nexus Phone',1,1,1),(14,'A Nokia phone with good pixel quality','2022-11-12 00:00:00','/api/images/mobiles/Nokia.png',100,NULL,'2022-11-02 00:00:00','A Nokia Phone',1,3,1),(15,'A nice formal shirts from the factory','2022-11-18 00:00:00','/api/images/clothes/harrington.png',800,NULL,'2022-11-04 00:00:00','A branded Men shirt',3,3,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bidding_history`
--

LOCK TABLES `bidding_history` WRITE;
/*!40000 ALTER TABLE `bidding_history` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2022-11-01 00:00:00','rad@gmail.com','radha','12345',1,2),(2,'2022-11-01 00:00:00','latha@gmail.com','latha','12345',1,2),(3,'2022-11-01 00:00:00','mallika@gmail.com','mallika','12345',1,2);
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

-- Dump completed on 2022-11-02 11:33:56
