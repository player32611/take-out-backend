-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: take_out_database
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `dish`
--

DROP TABLE IF EXISTS `dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dish` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜品名称',
  `category_id` bigint DEFAULT NULL COMMENT '分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片路径',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '菜品描述',
  `status` int DEFAULT '1' COMMENT '售卖状态',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dish`
--

LOCK TABLES `dish` WRITE;
/*!40000 ALTER TABLE `dish` DISABLE KEYS */;
INSERT INTO `dish` VALUES (1,'大酱炒笨蛋',8,17.00,'https://take-out-player32611.oss-cn-beijing.aliyuncs.com/0fa76f4f-42d6-4e7a-97d3-ed1d8442f24b.jpg','配料表：大妖精、琪露诺',1,'2026-08-23 13:17:30','2026-08-26 22:04:22',2,2),(2,'test',1,16.00,'https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg','test',1,'2026-08-23 13:58:32','2026-08-26 22:04:21',2,2),(7,'666',8,20.00,'https://take-out-player32611.oss-cn-beijing.aliyuncs.com/119c2371-41b4-41bb-aecb-e5736a6306e5.png','啊啊啊',1,'2026-08-23 23:04:42','2026-08-26 22:04:20',2,2),(8,'555',8,55555.00,'https://take-out-player32611.oss-cn-beijing.aliyuncs.com/970097cf-2c6c-4b1d-be75-4838cbd0aae9.avif','55555',1,'2026-08-24 02:00:20','2026-08-26 22:04:20',2,2),(9,'海鲜味增汤',1,20.00,'https://take-out-player32611.oss-cn-beijing.aliyuncs.com/d55ae9d2-d20c-4b0c-b20e-7a8317090cb3.jpg','配料表：八目鳗、海带',1,'2026-08-26 20:34:21','2026-09-01 14:49:10',2,2);
/*!40000 ALTER TABLE `dish` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-01 22:44:39
