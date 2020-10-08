/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.28 : Database - workdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`workdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `workdb`;

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目编号',
  `projectname` varchar(20) NOT NULL COMMENT '项目名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `project` */

insert  into `project`(`id`,`projectname`) values 
(1,'系统开发部'),
(2,'课件制作中心Java项目组'),
(3,'产品设计中心项目组');

/*Table structure for table `workorder` */

DROP TABLE IF EXISTS `workorder`;

CREATE TABLE `workorder` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工单编号',
  `projectid` int(11) NOT NULL COMMENT '项目编号',
  `executor` varchar(20) NOT NULL COMMENT '执行人',
  `description` varchar(255) NOT NULL COMMENT '任务描述',
  `orderlevel` int(11) NOT NULL COMMENT '工单级别',
  `createdate` date NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `workorder` */

insert  into `workorder`(`id`,`projectid`,`executor`,`description`,`orderlevel`,`createdate`) values 
(1,2,'adwaw','wdawd',1,'2020-10-05'),
(2,2,'awadaw','wadawd',1,'2020-10-05'),
(3,1,'执行人a','测试添加',2,'2020-10-05'),
(4,3,'测试2','测试2',2,'2020-10-05'),
(5,3,'测试添加222','测试添加212121',3,'2020-10-05');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
