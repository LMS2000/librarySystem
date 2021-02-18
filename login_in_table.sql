/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `login_in_table`
-- ----------------------------
DROP TABLE IF EXISTS `login_in_table`;
CREATE TABLE `login_in_table` (
  `u_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `is_state` tinyint(1) DEFAULT '1',
  `role` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_in_table
-- ----------------------------
INSERT INTO `login_in_table` VALUES ('1', '1805010202', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:40:10', null, '2021-02-16 15:29:12');
INSERT INTO `login_in_table` VALUES ('2', '1805010203', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:40:44', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('3', '1805010203', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:41:13', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('4', '1805010204', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:41:44', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('5', '1805010205', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:42:06', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('6', '1805010206', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:42:26', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('7', '1805010207', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:42:47', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('8', '1805010208', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_GUEST', '2021-02-02 17:43:08', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('9', '23222', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:44:53', null, '2021-02-16 17:00:55');
INSERT INTO `login_in_table` VALUES ('10', '234234', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:45:16', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('11', '284908631', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:45:42', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('12', '1234556', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:46:00', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('13', '12342131', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:46:18', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('14', 'test123', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_USER', '2021-02-02 17:46:43', null, '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('15', 'ming123', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '0', 'ROLE_USER', '2021-02-02 17:47:05', '2021-02-06 18:43:11', '2021-02-03 16:13:25');
INSERT INTO `login_in_table` VALUES ('16', 'admin', '$2a$10$TMTptQB5LiejBy/jXHWYKe7Cliq006K5i9NL9bedA37JNH0EerFgu', '1', 'ROLE_ADMIN', '2021-02-02 17:48:47', null, '2021-02-03 16:13:25');
