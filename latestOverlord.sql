CREATE DATABASE  IF NOT EXISTS `overlord` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `overlord`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: overlord
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaname` varchar(45) NOT NULL,
  `capacity` int(11) NOT NULL,
  `eventid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_area_id_idx` (`eventid`),
  CONSTRAINT `event_area_id` FOREIGN KEY (`eventid`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (9,'erg',25,11);
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(255) NOT NULL,
  `registrationnumber` varchar(45) NOT NULL,
  `contactdetail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (2,'Test Company','123 Address, Ireland','234234324','Brian Caul, 0857685431, caul1990@hotmail.com');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL,
  `eventname` varchar(45) NOT NULL,
  `capacity` int(11) NOT NULL,
  `start` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reset` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_event_id_idx` (`companyid`),
  CONSTRAINT `company_event_id` FOREIGN KEY (`companyid`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (11,2,'Home Event',25,'2015-12-12 11:11:01','2016-12-12 22:22:01',NULL,'Test');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `positionname` varchar(45) NOT NULL,
  `areaid` int(11) NOT NULL,
  `positionfunction` varchar(45) NOT NULL DEFAULT 'In',
  `positiontype` varchar(45) NOT NULL DEFAULT 'Internal',
  `numvisitors` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `postion_area_id_idx` (`areaid`),
  CONSTRAINT `postion_area_id` FOREIGN KEY (`areaid`) REFERENCES `area` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (14,'ergerg',9,'Entry/Exit','External',0);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stat`
--

DROP TABLE IF EXISTS `stat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `companyid` int(11) NOT NULL,
  `eventid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stat`
--

LOCK TABLES `stat` WRITE;
/*!40000 ALTER TABLE `stat` DISABLE KEYS */;
INSERT INTO `stat` VALUES (1,'Entry','2015-10-07 09:39:58',2,10),(2,'Entry','2015-10-07 09:40:01',2,10),(3,'Entry','2015-10-07 09:40:06',2,10),(4,'Entry','2015-10-07 09:40:07',2,10),(5,'Entry','2015-10-07 09:40:07',2,10),(6,'Exit','2015-10-07 09:40:12',2,10),(7,'Exit','2015-10-07 09:40:16',2,10),(8,'Entry','2015-10-07 09:40:22',2,10),(9,'Entry','2015-10-07 09:40:22',2,10),(10,'Entry','2015-10-07 09:40:26',2,10),(11,'Entry','2015-10-07 09:40:27',2,10),(12,'Entry','2015-10-07 10:02:33',2,10),(13,'Entry','2015-10-07 10:02:33',2,10),(14,'Entry','2015-10-07 10:02:33',2,10),(15,'Entry','2015-10-07 10:02:33',2,10),(16,'Entry','2015-10-07 10:02:33',2,10),(17,'Entry','2015-10-07 10:02:34',2,10),(18,'Entry','2015-10-07 10:02:34',2,10),(19,'Entry','2015-10-07 10:02:34',2,10),(20,'Entry','2015-10-07 10:02:40',2,10),(21,'Entry','2015-10-07 10:02:41',2,10),(22,'Entry','2015-10-07 10:02:41',2,10),(23,'Entry','2015-10-07 10:02:41',2,10),(24,'Entry','2015-10-07 10:02:42',2,10),(25,'Entry','2015-10-07 10:02:42',2,10),(26,'Entry','2015-10-07 10:02:42',2,10),(27,'Entry','2015-10-07 10:02:42',2,10),(28,'Entry','2015-10-07 10:02:43',2,10),(29,'Entry','2015-10-07 10:02:43',2,10),(30,'Exit','2015-10-07 10:03:00',2,10),(31,'Exit','2015-10-07 10:03:01',2,10),(32,'Exit','2015-10-07 10:03:01',2,10),(33,'Exit','2015-10-07 10:03:01',2,10),(34,'Entry','2015-10-07 10:05:16',2,10),(35,'Entry','2015-10-07 10:05:32',2,10),(36,'Entry','2015-10-07 10:05:32',2,10),(37,'Entry','2015-10-07 10:05:33',2,10),(38,'Exit','2015-10-07 10:05:43',2,10),(39,'Exit','2015-10-07 10:05:44',2,10),(40,'Exit','2015-10-07 10:05:44',2,10),(41,'Exit','2015-10-07 10:05:45',2,10),(42,'Entry','2015-10-07 10:05:58',2,10),(43,'Entry','2015-10-07 10:06:02',2,10),(44,'Entry','2015-10-07 10:06:02',2,10),(45,'Entry','2015-10-07 10:06:03',2,10),(46,'Entry','2015-10-07 10:06:03',2,10),(47,'Entry','2015-10-07 10:06:03',2,10),(48,'Entry','2015-10-07 10:06:03',2,10),(49,'Entry','2015-10-07 10:06:04',2,10),(50,'Entry','2015-10-07 10:06:04',2,10),(51,'Entry','2015-10-07 10:06:04',2,10),(52,'Entry','2015-10-07 10:06:04',2,10),(53,'Entry','2015-10-07 10:06:04',2,10),(54,'Entry','2015-10-07 10:06:04',2,10),(55,'Entry','2015-10-07 10:06:05',2,10),(56,'Entry','2015-10-07 10:06:05',2,10),(57,'Entry','2015-10-07 10:06:05',2,10),(58,'Exit','2015-10-07 10:06:16',2,10),(59,'Exit','2015-10-07 10:06:16',2,10),(60,'Exit','2015-10-07 10:06:17',2,10),(61,'Exit','2015-10-07 10:06:17',2,10),(62,'Exit','2015-10-07 10:06:17',2,10),(63,'Exit','2015-10-07 10:06:17',2,10),(64,'Exit','2015-10-07 10:06:17',2,10),(65,'Exit','2015-10-07 10:06:17',2,10),(66,'Exit','2015-10-07 10:06:18',2,10),(67,'Exit','2015-10-07 10:06:18',2,10),(68,'Exit','2015-10-07 10:06:18',2,10),(69,'Exit','2015-10-07 10:06:18',2,10),(70,'Exit','2015-10-07 10:06:18',2,10),(71,'Exit','2015-10-07 10:06:18',2,10),(72,'Exit','2015-10-07 10:06:18',2,10),(73,'Exit','2015-10-07 10:06:19',2,10);
/*!40000 ALTER TABLE `stat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `lastlogin` datetime DEFAULT CURRENT_TIMESTAMP,
  `enabled` varchar(255) DEFAULT 'true',
  `avatar` varchar(255) DEFAULT 'img/noavatar.png',
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyid` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `password` varchar(25) NOT NULL,
  `username` varchar(20) NOT NULL,
  `usertype` varchar(45) NOT NULL DEFAULT 'Attendant',
  `positionid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `company_userid_idx` (`companyid`),
  KEY `position_userid_idx` (`positionid`),
  CONSTRAINT `company_userid` FOREIGN KEY (`companyid`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `position_userid` FOREIGN KEY (`positionid`) REFERENCES `position` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('2015-10-06 12:09:06','true','img/noavatar.png',1,2,'test@test.com','Brian Caul','324324','admin','admin','Event Manger',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visitor`
--

DROP TABLE IF EXISTS `visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `entrytime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `exited` varchar(45) NOT NULL DEFAULT 'false',
  `positionid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `visitor_positionid_idx` (`positionid`),
  CONSTRAINT `visitor_positionid` FOREIGN KEY (`positionid`) REFERENCES `position` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visitor`
--

LOCK TABLES `visitor` WRITE;
/*!40000 ALTER TABLE `visitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-07 16:48:07
