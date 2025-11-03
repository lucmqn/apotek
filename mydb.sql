-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.44-log

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
-- Table structure for table `mbarang`
--

DROP TABLE IF EXISTS `mbarang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbarang` (
  `IDBarang` int(11) NOT NULL AUTO_INCREMENT,
  `Kode` varchar(50) DEFAULT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `IDKategori` int(11) DEFAULT NULL,
  `Satuan` varchar(50) DEFAULT NULL,
  `Beli` double DEFAULT NULL,
  `Jual` double DEFAULT NULL,
  `Keterangan` text,
  `IsAktif` enum('Ya','Tidak') DEFAULT NULL,
  PRIMARY KEY (`IDBarang`) USING BTREE,
  KEY `IDKategori` (`IDKategori`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbarang`
--

LOCK TABLES `mbarang` WRITE;
/*!40000 ALTER TABLE `mbarang` DISABLE KEYS */;
/*!40000 ALTER TABLE `mbarang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mkategori`
--

DROP TABLE IF EXISTS `mkategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mkategori` (
  `IDKategori` int(11) NOT NULL AUTO_INCREMENT,
  `Kode` varchar(10) DEFAULT NULL,
  `Nama` varchar(50) DEFAULT NULL,
  `Deskripsi` text,
  `IsAktif` enum('Ya','Tidak') DEFAULT NULL,
  PRIMARY KEY (`IDKategori`),
  UNIQUE KEY `Kode` (`Kode`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mkategori`
--

LOCK TABLES `mkategori` WRITE;
/*!40000 ALTER TABLE `mkategori` DISABLE KEYS */;
INSERT INTO `mkategori` VALUES (2,'RO0001','Rokok ','Rokok Apa saja AAAAQ','Ya'),(3,'SA0001','Sabun Batang','oKE MMM','Tidak'),(5,'SA0002','Sabun Cair','Cair','Ya');
/*!40000 ALTER TABLE `mkategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','d033e22ae348aeb5660fc2140aec35850c4da997'),(2,'hasan','b49b0b7881cc25d4aa89a7d0f1193b0648e9244f');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `variabel`
--

DROP TABLE IF EXISTS `variabel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `variabel` (
  `NamaVariabel` varchar(255) NOT NULL DEFAULT '',
  `Tipe` enum('Integer','Float','String','Boolean','DateTime') NOT NULL DEFAULT 'String',
  `Nilai` varchar(255) DEFAULT NULL,
  `Keterangan` text,
  PRIMARY KEY (`NamaVariabel`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='mencatat nilai variabel general yg dipakai dlm program';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `variabel`
--

LOCK TABLES `variabel` WRITE;
/*!40000 ALTER TABLE `variabel` DISABLE KEYS */;
INSERT INTO `variabel` VALUES ('Kode_Kasir','String','KS1',NULL),('Kode_TransJual_Num','String','NNNN.MMYY',NULL),('Kode_TransJual_Prefix','String','JL/',NULL),('ppn','Integer','11',NULL);
/*!40000 ALTER TABLE `variabel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-10 14:31:32
