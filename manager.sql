/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `m_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `manager_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `login_account_id` bigint(11) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`m_id`),
  KEY `manager_login_id` (`login_account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', '辰', '232222@qq.com', '9', null, null);
INSERT INTO `manager` VALUES ('2', '明', '234234@qq.com', '10', null, null);
INSERT INTO `manager` VALUES ('3', '明月', '284908631@qq.com', '11', null, null);
INSERT INTO `manager` VALUES ('11', 'root', '123456@qq.com', '12', null, null);
INSERT INTO `manager` VALUES ('12', '测试员', 'test@qq.com', '13', null, null);
INSERT INTO `manager` VALUES ('13', '明月', '1@qq.com', '14', null, null);
