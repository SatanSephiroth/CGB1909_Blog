/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.5-10.3.11-MariaDB : Database - my_blog_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`my_blog_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `my_blog_db`;

/*Table structure for table `tb_admin_user` */

DROP TABLE IF EXISTS `tb_admin_user`;

CREATE TABLE `tb_admin_user` (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  `permission` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`admin_user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `tb_admin_user` */

insert  into `tb_admin_user`(`admin_user_id`,`login_user_name`,`login_password`,`nick_name`,`locked`,`permission`) values (1,'admin','e10adc3949ba59abbe56e057f20f883e','超级管理员',0,NULL),(2,'冈坂日川','c33367701511b4f6020ec61ded352059','低能管理员',0,NULL),(3,'后岩伍尺','6c44e5cd17f0019c64b042e4a745412a','杜崎燕',0,NULL),(4,'松下库代子','4607e782c4d86fd5364d7e4508bb10d9','杜子腾',0,NULL),(24,'雷哥','202cb962ac59075b964b07152d234b70','雷哥',0,NULL);

/*Table structure for table `tb_blog` */

DROP TABLE IF EXISTS `tb_blog`;

CREATE TABLE `tb_blog` (
  `blog_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '博客表主键id',
  `blog_title` varchar(200) NOT NULL COMMENT '博客标题',
  `blog_sub_url` varchar(200) NOT NULL COMMENT '博客自定义路径url',
  `blog_cover_image` varchar(200) NOT NULL COMMENT '博客封面图',
  `blog_content` mediumtext NOT NULL COMMENT '博客内容',
  `blog_category_id` int(11) NOT NULL COMMENT '博客分类id',
  `blog_category_name` varchar(50) NOT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` varchar(200) NOT NULL COMMENT '博客标签',
  `blog_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-草稿 1-发布',
  `blog_views` bigint(20) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `enable_comment` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '添加时间',
  `update_time` datetime DEFAULT current_timestamp() COMMENT '修改时间',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog` */

insert  into `tb_blog`(`blog_id`,`blog_title`,`blog_sub_url`,`blog_cover_image`,`blog_content`,`blog_category_id`,`blog_category_name`,`blog_tags`,`blog_status`,`blog_views`,`enable_comment`,`is_deleted`,`create_time`,`update_time`,`user_id`) values (1,'测试文章','','http://www.myblog.com/admin/dist/img/rand/33.jpg','文章内容测试',20,'About','世界上有一个很可爱的人,现在这个人就在看这句话',1,259,0,1,'2019-12-19 14:31:15','2019-12-19 14:31:15',1),(2,'又测试文章了','','/admin/dist/img/rand/13.jpg','再次测试文章',24,'日常随笔','目录',1,24,0,0,'2019-12-19 15:42:05','2019-12-19 15:42:05',1),(3,'最后一次测试文章了吧','','/admin/dist/img/rand/36.jpg','文章测试',22,'Java基础','Spring,SpringMVC,MyBatis,easyUI,AdminLte3',1,66,0,0,'2019-12-19 22:12:53','2019-12-19 22:12:53',1),(4,'ccc','','/admin/dist/img/rand/29.jpg','aaa',24,'日常随笔','SpringBoot,入门教程,实战教程,spring-boot企业级开发',1,24,0,0,'2019-12-20 09:58:54','2019-12-20 09:58:54',1),(6,'test','','/admin/dist/img/rand/17.jpg','test',24,'日常随笔','test',1,5,0,1,'2019-12-20 14:31:37','2019-12-20 14:31:37',3),(7,'文章发布功能最终测试','','http://www.myblog.com/admin/dist/img/rand/36.jpg','文章发布功能最终测试',24,'日常随笔','最终测试',1,21,0,1,'2019-12-23 13:36:10','2019-12-23 13:36:10',2),(8,'About','about','http://localhost/admin/dist/img/rand/35.jpg','感谢各位组员的辛勤付出,愿各位组员今后事业有成.\n福如东海',24,'日常随笔','愿景',1,5,0,1,'2019-12-23 14:07:29','2019-12-23 14:07:29',1),(9,'测试1','沙发士大夫','http://localhost/admin/dist/img/rand/6.jpg','233333',25,'虫虫','打发士大夫,s',1,4,0,1,'2019-12-23 17:39:30','2019-12-23 17:39:30',1);

/*Table structure for table `tb_blog_category` */

DROP TABLE IF EXISTS `tb_blog_category`;

CREATE TABLE `tb_blog_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) NOT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) NOT NULL COMMENT '分类的图标',
  `category_rank` int(11) NOT NULL DEFAULT 1 COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog_category` */

insert  into `tb_blog_category`(`category_id`,`category_name`,`category_icon`,`category_rank`,`is_deleted`,`create_time`) values (20,'About','/admin/dist/img/category/10.png',9,0,'2019-12-19 15:28:49'),(22,'Java基础','/admin/dist/img/category/02.png',19,0,'2018-12-20 10:42:25'),(24,'日常随笔','/admin/dist/img/category/06.png',27,0,'2018-12-20 10:43:21'),(25,'虫虫','/admin/dist/img/category/07.png',2,0,'2019-12-23 17:39:01');

/*Table structure for table `tb_blog_comment` */

DROP TABLE IF EXISTS `tb_blog_comment`;

CREATE TABLE `tb_blog_comment` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` varchar(50) NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `website_url` varchar(50) NOT NULL DEFAULT '' COMMENT '网址',
  `comment_body` varchar(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '评论提交时间',
  `commentator_ip` varchar(20) NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '回复时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` tinyint(4) DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog_comment` */

insert  into `tb_blog_comment`(`comment_id`,`blog_id`,`commentator`,`email`,`website_url`,`comment_body`,`comment_create_time`,`commentator_ip`,`reply_body`,`reply_create_time`,`comment_status`,`is_deleted`) values (26,1,'团队','224683568@qq.com','','第一条评论','2019-12-18 10:12:19','','','2019-12-18 21:13:31',1,0),(27,1,'冈坂日川','13506183370@163.com','','2L','2019-12-18 14:31:18','','','2019-12-18 14:31:18',1,0),(28,2,'冈門偏佐','13506183370@163.com','','完事','2019-12-23 13:58:32','','','2019-12-23 13:58:32',1,0),(29,2,'冈門鸿重','13506183370@163.com','','总算ok了','2019-12-23 13:59:40','','','2019-12-23 13:59:40',1,0),(30,3,'梅川一夫','13506183370@qq.com','','希望是最后一次了','2019-12-23 14:00:32','','','2019-12-23 14:00:32',1,0),(31,8,'大厦','123456@qq.com','','6666','2019-12-23 17:38:05','','','2019-12-23 17:38:05',1,1);

/*Table structure for table `tb_blog_tag` */

DROP TABLE IF EXISTS `tb_blog_tag`;

CREATE TABLE `tb_blog_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) NOT NULL COMMENT '标签名称',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog_tag` */

insert  into `tb_blog_tag`(`tag_id`,`tag_name`,`is_deleted`,`create_time`) values (57,'世界上有一群厉害的人',0,'2019-12-19 00:31:15'),(58,'现在这个人就在看这句话',0,'2019-12-19 00:31:15'),(66,'Spring',0,'2019-12-19 10:55:14'),(67,'SpringMVC',0,'2019-12-19 10:55:14'),(68,'MyBatis',0,'2019-12-19 10:55:14'),(69,'easyUI',0,'2019-12-19 10:55:14'),(127,'目录',0,'2019-12-19 15:41:39'),(128,'AdminLte3',0,'2019-12-19 15:46:16'),(130,'SpringBoot',0,'2019-12-20 09:58:54'),(131,'入门教程',0,'2019-12-20 09:58:54'),(132,'实战教程',0,'2019-12-20 09:58:54'),(133,'spring-boot企业级开发',0,'2019-12-20 09:58:54'),(134,'test',0,'2019-12-20 14:31:37'),(135,'最终测试',0,'2019-12-23 13:36:10'),(136,'世界上有一个很可爱的人',0,'2019-12-23 14:03:37'),(137,'愿景',0,'2019-12-23 14:07:29'),(138,'打发士大夫',0,'2019-12-23 17:39:30'),(139,'s',0,'2019-12-23 17:39:30');

/*Table structure for table `tb_blog_tag_relation` */

DROP TABLE IF EXISTS `tb_blog_tag_relation`;

CREATE TABLE `tb_blog_tag_relation` (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime DEFAULT current_timestamp() COMMENT '添加时间',
  PRIMARY KEY (`relation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=288 DEFAULT CHARSET=utf8;

/*Data for the table `tb_blog_tag_relation` */

insert  into `tb_blog_tag_relation`(`relation_id`,`blog_id`,`tag_id`,`create_time`) values (269,2,127,'2019-05-13 09:56:49'),(270,4,130,'2019-05-13 09:58:54'),(271,4,131,'2019-05-13 09:58:54'),(272,4,132,'2019-05-13 09:58:54'),(273,4,133,'2019-05-13 09:58:54'),(274,3,66,'2019-05-13 10:07:27'),(275,3,67,'2019-05-13 10:07:27'),(276,3,68,'2019-05-13 10:07:27'),(277,3,69,'2019-05-13 10:07:27'),(278,3,128,'2019-05-13 10:07:27'),(279,6,134,'2019-12-20 14:31:37'),(280,7,135,'2019-12-23 13:36:10'),(281,1,58,'2019-12-23 14:03:37'),(282,1,136,'2019-12-23 14:03:37'),(285,8,137,'2019-12-23 17:36:53'),(286,9,138,'2019-12-23 17:39:30'),(287,9,139,'2019-12-23 17:39:30');

/*Table structure for table `tb_config` */

DROP TABLE IF EXISTS `tb_config`;

CREATE TABLE `tb_config` (
  `config_name` varchar(100) NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '修改时间',
  PRIMARY KEY (`config_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_config` */

insert  into `tb_config`(`config_name`,`config_value`,`create_time`,`update_time`) values ('footerAbout','your personal blog. have fun.','2018-11-11 20:33:23','2018-11-12 11:58:06'),('footerCopyRight','2019 CGB1909无锡宝龙','2018-11-11 20:33:31','2018-11-12 11:58:06'),('footerICP','浙ICP备17008806号-3','2018-11-11 20:33:27','2018-11-12 11:58:06'),('footerPoweredBy','http://www.tmooc.cn/','2018-11-11 20:33:36','2018-11-12 11:58:06'),('footerPoweredByURL','http://www.tmooc.cn/','2018-11-11 20:33:39','2018-11-12 11:58:06'),('websiteDescription','personal blog是SpringBoot2+Thymeleaf+Mybatis建造的个人博客网站.SpringBoot实战博客源码.个人博客搭建','2018-11-11 20:33:04','2018-11-11 22:05:14'),('websiteIcon','/admin/dist/img/favicon.png','2018-11-11 20:33:11','2018-11-11 22:05:14'),('websiteLogo','/admin/dist/img/logo2.png','2018-11-11 20:33:08','2018-11-11 22:05:14'),('websiteName','个人博客','2018-11-11 20:33:01','2018-11-11 22:05:14'),('yourAvatar','/admin/dist/img/13.png','2018-11-11 20:33:14','2019-05-07 21:56:23'),('yourEmail','2449207463@qq.com','2018-11-11 20:33:17','2019-05-07 21:56:23'),('yourName','','2018-11-11 20:33:20','2019-05-07 21:56:23');

/*Table structure for table `tb_link` */

DROP TABLE IF EXISTS `tb_link`;

CREATE TABLE `tb_link` (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) NOT NULL COMMENT '网站名称',
  `link_url` varchar(100) NOT NULL COMMENT '网站链接',
  `link_description` varchar(100) NOT NULL COMMENT '网站描述',
  `link_rank` int(11) NOT NULL DEFAULT 0 COMMENT '用于列表排序',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime NOT NULL DEFAULT current_timestamp() COMMENT '添加时间',
  PRIMARY KEY (`link_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `tb_link` */

insert  into `tb_link`(`link_id`,`link_type`,`link_name`,`link_url`,`link_description`,`link_rank`,`is_deleted`,`create_time`) values (1,0,'tqr','rqwe','rqw',0,1,'2018-10-22 18:57:52'),(2,2,'十三的GitHub','https://github.com/ZHENFENG13','十三分享代码的地方',1,1,'2018-10-22 19:41:04'),(3,2,'十三的博客','http://13blog.site','个人独立博客13blog',14,1,'2018-10-22 19:53:34'),(4,1,'CSDN 图文课','https://gitchat.csdn.net','IT优质内容平台',6,0,'2018-10-22 19:55:55'),(5,2,'十三的博客园','https://www.cnblogs.com/han-1034683568','最开始写博客的地方',17,1,'2018-10-22 19:56:14'),(6,1,'CSDN','https://www.csdn.net/','CSDN-专业IT技术社区官网',4,0,'2018-10-22 19:56:47'),(7,0,'梁桂钊的博客','http://blog.720ui.com','后端攻城狮',1,1,'2018-10-22 20:01:38'),(8,0,'猿天地','http://cxytiandi.com','一个综合性的网站,以程序猿用户为主,提供各种开发相关的内容',12,0,'2018-10-22 20:02:41'),(9,0,'Giraffe Home','https://yemengying.com/','Giraffe Home',0,1,'2018-10-22 20:27:04'),(10,0,'纯洁的微笑','http://www.ityouknow.com','该大神在Github和码云分享了不少开源项目和教学',3,0,'2018-10-22 20:27:16'),(11,0,'afsdf','http://localhost:28080/admin/links','fad',0,1,'2018-10-22 20:27:26'),(12,1,'afsdf','http://localhost','fad1',0,1,'2018-10-24 14:04:18'),(13,0,'郭赵晖','http://guozh.net/','老郭三分地',0,1,'2019-04-24 15:30:19'),(14,0,'dalaoyang','https://www.dalaoyang.cn/','dalaoyang',0,1,'2019-04-24 15:31:50'),(15,0,'达内','http://www.tmooc.cn/','达内精品在线TMOOC',0,0,'2019-04-24 15:32:19'),(16,1,'Github','https://github.com/','开源练习项目查找',1,0,'2019-04-24 16:03:48'),(17,2,'《SSM 搭建精美实用的管理系统》','https://gitbook.cn/gitchat/column/5b4dae389bcda53d07056bc9','Spring+SpringMVC+MyBatis实战课程',18,0,'2019-04-24 16:06:52'),(18,2,'《Spring Boot 入门及前后端分离项目实践》','https://www.shiyanlou.com/courses/1244','SpringBoot实战课程',19,0,'2019-04-24 16:07:27'),(19,2,'《玩转Spring Boot 系列》','https://www.shiyanlou.com/courses/1274','SpringBoot实战课程',20,0,'2019-04-24 16:10:30'),(20,1,'码云','https://gitee.com/','国内寻找开源项目',2,0,'2019-12-23 13:18:36'),(21,2,'百度','https://www.baidu.com','百度一下你就知道',1,0,'2019-12-23 17:42:59');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
