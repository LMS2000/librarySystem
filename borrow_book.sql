/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `borrow_book`
-- ----------------------------
DROP TABLE IF EXISTS `borrow_book`;
CREATE TABLE `borrow_book` (
  `bb_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `card_id` bigint(11) DEFAULT '0',
  `book_id` bigint(11) DEFAULT '0',
  `borrow_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `return_date` datetime DEFAULT NULL,
  `illegal` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `manager_id` bigint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bb_id`),
  KEY `borrow_book_card_id` (`card_id`),
  KEY `borrow_book_book_id` (`book_id`),
  KEY `borrow_book_manager_id` (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow_book
-- ----------------------------
INSERT INTO `borrow_book` VALUES ('20', '1805010202', '1', '2020-06-11 09:37:10', '2020-06-12 09:37:18', '2020-06-11 09:48:07', '', '1', null, null);
INSERT INTO `borrow_book` VALUES ('21', '1805010206', '5', '2020-04-08 09:37:40', '2020-06-08 09:38:14', '2020-06-11 10:50:07', '已逾期3天', '1', null, null);
INSERT INTO `borrow_book` VALUES ('22', '1805010207', '1', '2020-06-11 09:47:44', '2020-08-10 09:47:52', '2020-06-11 09:50:07', '', '1', null, null);
INSERT INTO `borrow_book` VALUES ('24', '1805010202', '1', '2020-06-02 09:55:04', '2020-06-03 09:55:16', '2020-06-11 09:56:02', '已逾期8天', '1', null, null);
INSERT INTO `borrow_book` VALUES ('28', '1805010204', '2', '2020-04-07 10:12:28', '2020-06-06 10:12:41', null, null, '1', null, null);
INSERT INTO `borrow_book` VALUES ('29', '1805010202', '6', '2020-06-11 08:19:56', '2020-06-12 08:20:07', null, null, '2', null, null);
INSERT INTO `borrow_book` VALUES ('30', '1805010202', '1', '2021-02-16 17:02:37', '2021-02-16 17:02:45', '2020-06-12 01:48:07', null, '0', null, '2021-02-16 19:52:04');
