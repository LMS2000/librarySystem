/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:31:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rule`
-- ----------------------------
DROP TABLE IF EXISTS `rule`;
CREATE TABLE `rule` (
  `r_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `borrow_num` bigint(11) DEFAULT NULL COMMENT '闂勬劕鍩楅張顒佹殶',
  `limit_day` bigint(11) DEFAULT NULL COMMENT '闂勬劕鍩楁径鈺傛殶',
  `borrow_library` varchar(20) DEFAULT NULL,
  `overtime_fee` decimal(10,3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rule
-- ----------------------------
INSERT INTO `rule` VALUES ('1', '1', '23', '1、2、3', '0.200', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('2', '1', '23', '2、3', '0.200', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('3', '1', '23', '1、2、3', '0.200', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('4', '1', '23', '1、2、3', '0.300', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('5', '1', '23', '1', '0.300', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('6', '1', '23', '1、2、3', '0.300', null, '2021-02-10 21:03:33');
INSERT INTO `rule` VALUES ('7', '1', '23', '1、3', '0.300', null, '2021-02-10 21:03:33');
