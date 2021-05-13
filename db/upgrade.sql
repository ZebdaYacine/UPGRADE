-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: upgrade
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Table structure for table `employer`
--

DROP TABLE IF EXISTS `employer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `recruitmentDate` date DEFAULT NULL,
  `socialStatus` varchar(50) DEFAULT NULL,
  `deploma` varchar(50) DEFAULT NULL,
  `nbrchildren` int(11) DEFAULT NULL,
  `note` int(11) DEFAULT NULL,
  `nbrFonrmations` int(11) DEFAULT NULL,
  `experience` int(11) DEFAULT NULL,
  `idGrade` int(11) NOT NULL,
  `idOffice` int(11) NOT NULL,
  `discipline` int(11) DEFAULT '0',
  `lastUpgarde` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `firstName` (`firstName`,`lastName`,`birthDate`),
  KEY `idGrade` (`idGrade`),
  KEY `idOffice` (`idOffice`),
  CONSTRAINT `employer_ibfk_1` FOREIGN KEY (`idGrade`) REFERENCES `grade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `employer_ibfk_2` FOREIGN KEY (`idOffice`) REFERENCES `office` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employer`
--

LOCK TABLES `employer` WRITE;
/*!40000 ALTER TABLE `employer` DISABLE KEYS */;
INSERT INTO `employer` VALUES (1,'zed','zed','0658185867','1996-05-13','2020-05-07','Marié','Master informatique',1,12,1,1,1,1,33,'2020-05-07'),(2,'oussama','oussama','0658185860','1990-05-13','2020-05-07','Marié','Master informatique',1,12,1,1,1,1,100,'2020-05-07'),(3,'sed','sed','0658185861','1990-05-13','2016-05-07','Marié','Master informatique',2,12,1,5,1,1,66,'2016-05-07'),(4,'med','med','0658185864','1990-05-13','2018-05-07','Marié','Master informatique',2,15,1,3,2,2,100,'2018-05-07'),(5,'hod','hod','0658185800','1990-05-13','2018-05-07','célibataire','Master informatique',0,15,1,3,2,2,100,'2018-05-07'),(6,'nos','nos','0658185801','1990-05-13','2018-05-07','célibataire','Master informatique',0,15,1,3,2,2,33,'2018-05-07');
/*!40000 ALTER TABLE `employer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `deploma` varchar(50) DEFAULT NULL,
  `note` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'grade 1','Master informatique ',40),(2,'grade 2','Master 2 droit',40);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `need`
--

DROP TABLE IF EXISTS `need`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `need` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idOffice` int(11) NOT NULL,
  `idSpeciality` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idOffice` (`idOffice`),
  KEY `idSpeciality` (`idSpeciality`),
  CONSTRAINT `need_ibfk_1` FOREIGN KEY (`idOffice`) REFERENCES `office` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `need_ibfk_2` FOREIGN KEY (`idSpeciality`) REFERENCES `speciality` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `need`
--

LOCK TABLES `need` WRITE;
/*!40000 ALTER TABLE `need` DISABLE KEYS */;
INSERT INTO `need` VALUES (1,1,1),(2,2,2),(3,3,2);
/*!40000 ALTER TABLE `need` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `office`
--

DROP TABLE IF EXISTS `office`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `office`
--

LOCK TABLES `office` WRITE;
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` VALUES (1,'ofc1'),(2,'ofc2'),(3,'ofc3');
/*!40000 ALTER TABLE `office` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `speciality`
--

DROP TABLE IF EXISTS `speciality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `speciality` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `speciality`
--

LOCK TABLES `speciality` WRITE;
/*!40000 ALTER TABLE `speciality` DISABLE KEYS */;
INSERT INTO `speciality` VALUES (1,'sp1'),(2,'sp2');
/*!40000 ALTER TABLE `speciality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work`
--

DROP TABLE IF EXISTS `work`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idEmployer` int(11) NOT NULL,
  `idWorkingDate` int(11) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idEmployer` (`idEmployer`,`idWorkingDate`),
  KEY `idWorkingDate` (`idWorkingDate`),
  CONSTRAINT `work_ibfk_1` FOREIGN KEY (`idEmployer`) REFERENCES `employer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `work_ibfk_2` FOREIGN KEY (`idWorkingDate`) REFERENCES `workingdate` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work`
--

LOCK TABLES `work` WRITE;
/*!40000 ALTER TABLE `work` DISABLE KEYS */;
INSERT INTO `work` VALUES (56,1,1,'présent'),(57,2,1,'présent'),(58,3,1,'présent'),(59,4,1,'présent'),(60,5,1,'présent'),(61,6,1,'présent'),(62,1,2,'absent'),(63,2,2,'présent'),(64,3,2,'absent'),(65,4,2,'présent'),(66,5,2,'présent'),(67,6,2,'absent'),(68,1,3,'absent'),(69,2,3,'présent'),(70,3,3,'présent'),(71,4,3,'présent'),(72,5,3,'présent'),(73,6,3,'absent');
/*!40000 ALTER TABLE `work` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workingdate`
--

DROP TABLE IF EXISTS `workingdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workingdate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dateWorke` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dateWorke` (`dateWorke`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workingdate`
--

LOCK TABLES `workingdate` WRITE;
/*!40000 ALTER TABLE `workingdate` DISABLE KEYS */;
INSERT INTO `workingdate` VALUES (1,'2022-12-18'),(2,'2022-12-20'),(3,'2022-12-21');
/*!40000 ALTER TABLE `workingdate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-13 23:37:58
