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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身份证号',
  `status` int DEFAULT '1' COMMENT '账号状态 1正常 0锁定',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人id',
  `update_user` bigint DEFAULT NULL COMMENT '最后修改人id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='员工表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'test','test','123456','11111111111','男','111111111111111111',1,'2026-08-10 22:06:28','2026-08-10 22:06:28',1,1),(21,'1','1','e10adc3949ba59abbe56e057f20f883e','1','男',NULL,1,'2026-08-18 21:25:30','2026-08-18 21:25:30',1,1),(35,'2','2','e10adc3949ba59abbe56e057f20f883e','1','男',NULL,0,'2026-08-19 21:38:16','2026-08-24 01:59:36',1,1),(37,'33','33','e10adc3949ba59abbe56e057f20f883e','1','男','11213',0,'2026-08-19 21:38:49','2026-08-22 20:11:00',1,1),(39,'444','44','e10adc3949ba59abbe56e057f20f883e','444444','男','4444',1,'2026-08-19 21:44:17','2026-08-22 20:10:52',2,2),(40,'6','6','e10adc3949ba59abbe56e057f20f883e','6','男',NULL,0,'2026-08-19 22:04:37','2026-08-21 23:00:33',2,2),(46,'7','7','e10adc3949ba59abbe56e057f20f883e','6','男',NULL,0,'2026-08-19 22:05:06','2026-08-22 13:56:31',2,2),(47,'88','88','e10adc3949ba59abbe56e057f20f883e','888','男',NULL,1,'2026-08-21 21:22:43','2026-08-21 21:22:43',2,2),(48,'99','99','e10adc3949ba59abbe56e057f20f883e','999','男',NULL,1,'2026-08-21 21:22:48','2026-08-21 21:22:48',2,2),(49,'123','123','e10adc3949ba59abbe56e057f20f883e','123','男',NULL,1,'2026-08-21 21:22:58','2026-08-21 21:22:58',2,2),(50,'1234','1234','e10adc3949ba59abbe56e057f20f883e','555','女',NULL,1,'2026-08-21 21:24:07','2026-08-21 21:24:07',2,2),(51,'1233','1233','e10adc3949ba59abbe56e057f20f883e','1233','女',NULL,1,'2026-08-21 21:33:16','2026-08-21 21:33:16',2,2),(52,'qwe','qwe','e10adc3949ba59abbe56e057f20f883e','112','女',NULL,1,'2026-08-21 21:58:38','2026-08-21 21:58:38',2,2),(53,'asasa','112as','e10adc3949ba59abbe56e057f20f883e','66789','男',NULL,1,'2026-08-21 21:58:50','2026-08-21 21:58:50',2,2),(54,'阿斯顿','aaa','e10adc3949ba59abbe56e057f20f883e','123452','女','114514',1,'2026-08-22 13:37:58','2026-08-22 13:37:58',2,2);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
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
