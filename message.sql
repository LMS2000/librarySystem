/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:31:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `message_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(11) DEFAULT '0',
  `detail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `public_date` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`message_id`),
  KEY `message_card_id` (`card_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1805010202', '冲鸭，一起来看书', '2020-06-01 23:11:59', null, null);
INSERT INTO `message` VALUES ('2', '1805010202', '我爱学习', '2020-05-29 23:12:03', null, null);
INSERT INTO `message` VALUES ('3', '1805010202', '图书馆的书质量很好，图书管理员态度非常好，系统管理员也非常热心，太棒了', '2020-06-04 23:12:24', null, null);
INSERT INTO `message` VALUES ('6', '1805010202', '咋回事', '2020-06-12 08:34:18', null, null);
