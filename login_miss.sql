/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_miss`
-- ----------------------------
DROP TABLE IF EXISTS `login_miss`;
CREATE TABLE `login_miss` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `u_id` bigint(11) DEFAULT '0',
  `miss_number` int(3) DEFAULT '0',
  `miss_time` datetime DEFAULT NULL,
  `miss_flag` tinyint(1) DEFAULT '1',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_miss
-- ----------------------------
INSERT INTO `login_miss` VALUES ('2', '1', '0', '2021-02-16 21:45:32', '0', '2021-02-16 21:45:06', '2021-02-16 21:45:25');
