CREATE DATABASE  IF NOT EXISTS `flippedclassroom` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `flippedclassroom`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: flippedclassroom
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `assignment`
--

DROP TABLE IF EXISTS `assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment` (
  `assignment_id` bigint(20) NOT NULL,
  `assignment_name` varchar(255) DEFAULT NULL,
  `assignment_path` varchar(255) DEFAULT NULL,
  `assignment_size` int(11) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `subid` varchar(255) DEFAULT NULL,
  `upload_time` datetime(6) DEFAULT NULL,
  `max_marks` int(11) NOT NULL,
  PRIMARY KEY (`assignment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment`
--

LOCK TABLES `assignment` WRITE;
/*!40000 ALTER TABLE `assignment` DISABLE KEYS */;
INSERT INTO `assignment` VALUES (1,'Agnibha_Chakraborty_CV_Final (1).pdf_IT_PCC601_application.pdf','C:\\Users\\ANUPAM\\Desktop\\RIJU\\Proj_09\\4th_Year_Project_updated\\FlippedClassroom\\upload_ass\\Agnibha_Chakraborty_CV_Final (1).pdf_IT_PCC601_application.pdf',168233,'PCC601','IT','2025-02-11 19:51:13.000000',0),(2,'Riju-Portrait(1)-min.jpg_CSE_PCC602_image.jpeg','C:\\Users\\ANUPAM\\Desktop\\RIJU\\Proj_09\\4th_Year_Project_updated\\FlippedClassroom\\upload_ass\\Riju-Portrait(1)-min.jpg_CSE_PCC602_image.jpeg',409692,'PCC602','CSE','2025-02-11 20:15:36.000000',25),(3,'Agnibha_Chakraborty_CV_Final (1).pdf_CSE_PCC602_application.pdf','C:\\Users\\ANUPAM\\Desktop\\RIJU\\Proj_09\\4th_Year_Project_updated\\FlippedClassroom\\upload_ass\\Agnibha_Chakraborty_CV_Final (1).pdf_CSE_PCC602_application.pdf',168233,'PCC602','CSE','2025-02-11 20:16:12.000000',60),(4,'Agnibha_Chakraborty_CV_Final.docx_CSE_PCC602_application.vnd.openxmlformats-officedocument.wordprocessingml.document','C:\\Users\\ANUPAM\\Desktop\\RIJU\\Proj_09\\4th_Year_Project_updated\\FlippedClassroom\\upload_ass\\Agnibha_Chakraborty_CV_Final.docx_CSE_PCC602_application.vnd.openxmlformats-officedocument.wordprocessingml.document',78082,'PCC602','CSE','2025-02-11 21:09:29.000000',100),(52,'certificate (1).jpg_CSE_PCC602_image.jpeg','C:\\Users\\ANUPAM\\Desktop\\RIJU\\Proj_09\\4th_Year_Project_updated\\FlippedClassroom\\upload_ass\\certificate (1).jpg_CSE_PCC602_image.jpeg',60760,'PCC602','CSE','2025-02-12 23:22:44.000000',34);
/*!40000 ALTER TABLE `assignment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-14 16:18:42
