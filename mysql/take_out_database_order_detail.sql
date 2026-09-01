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
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `order_id` bigint DEFAULT NULL COMMENT '订单id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `number` int DEFAULT NULL COMMENT '商品数量',
  `amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单明细表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'海鲜味增汤','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/d55ae9d2-d20c-4b0c-b20e-7a8317090cb3.jpg',1,9,NULL,'常温,不要香菜',3,20.00),(2,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',1,2,NULL,NULL,1,16.00),(3,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',2,2,NULL,NULL,1,16.00),(4,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',3,2,NULL,NULL,1,16.00),(5,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',4,NULL,9,NULL,1,55.00),(6,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',5,2,NULL,NULL,1,16.00),(7,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',6,2,NULL,NULL,1,16.00),(8,'海鲜味增汤','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/d55ae9d2-d20c-4b0c-b20e-7a8317090cb3.jpg',6,9,NULL,'常温,不要香菜',2,20.00),(9,'大酱炒笨蛋','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/0fa76f4f-42d6-4e7a-97d3-ed1d8442f24b.jpg',6,1,NULL,'特辣',1,17.00),(10,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',6,NULL,9,NULL,2,55.00),(11,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',7,2,NULL,NULL,1,16.00),(12,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',8,2,NULL,NULL,4,16.00),(13,'开心乐园餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/f5c39ae1-63d9-4254-92f4-18556a35062a.jpg',8,NULL,6,NULL,4,56.00),(14,'开心乐园餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/f5c39ae1-63d9-4254-92f4-18556a35062a.jpg',9,NULL,6,NULL,3,56.00),(15,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',9,NULL,9,NULL,4,55.00),(16,'666','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/119c2371-41b4-41bb-aecb-e5736a6306e5.png',9,7,NULL,'冷',1,20.00),(17,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',10,2,NULL,NULL,1,16.00),(18,'海鲜味增汤','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/d55ae9d2-d20c-4b0c-b20e-7a8317090cb3.jpg',10,9,NULL,'常温,不要香菜',2,20.00),(19,'大酱炒笨蛋','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/0fa76f4f-42d6-4e7a-97d3-ed1d8442f24b.jpg',10,1,NULL,'特辣',1,17.00),(20,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',10,NULL,9,NULL,2,55.00),(21,'test','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/b41cd7a8-0b16-4a83-8e6b-c0ffc3568003.jpg',11,2,NULL,NULL,3,16.00),(22,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',12,NULL,9,NULL,5,55.00),(23,'开心乐园餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/f5c39ae1-63d9-4254-92f4-18556a35062a.jpg',13,NULL,6,NULL,1,56.00),(24,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',14,NULL,9,NULL,5,55.00),(25,'666','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/119c2371-41b4-41bb-aecb-e5736a6306e5.png',15,7,NULL,'冷',6,20.00),(26,'开心乐园餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/f5c39ae1-63d9-4254-92f4-18556a35062a.jpg',16,NULL,6,NULL,3,56.00),(27,'超值单人餐','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/ebcea59a-c49c-41de-8be1-37d5115cbdaa.avif',16,NULL,9,NULL,2,55.00),(28,'大酱炒笨蛋','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/0fa76f4f-42d6-4e7a-97d3-ed1d8442f24b.jpg',17,1,NULL,'特辣',3,17.00),(29,'555','https://take-out-player32611.oss-cn-beijing.aliyuncs.com/970097cf-2c6c-4b1d-be75-4838cbd0aae9.avif',18,8,NULL,'不要香菜,特辣',1,55555.00);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-09-01 22:44:38
