/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : libraryproject

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2021-02-18 16:30:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `book_sort`
-- ----------------------------
DROP TABLE IF EXISTS `book_sort`;
CREATE TABLE `book_sort` (
  `bs_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `bs_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '''''' COMMENT '娑旓妇鐫勭猾璇茬€烽崥?',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book_sort
-- ----------------------------
INSERT INTO `book_sort` VALUES ('1', '未分类', '不可删除', null, null);
INSERT INTO `book_sort` VALUES ('2', '护理', '护理护理', null, null);
INSERT INTO `book_sort` VALUES ('3', '编程', '编程编程', null, null);
INSERT INTO `book_sort` VALUES ('4', '艺术', '', null, null);
INSERT INTO `book_sort` VALUES ('5', '管理', null, null, null);
INSERT INTO `book_sort` VALUES ('6', '法律', null, null, null);
INSERT INTO `book_sort` VALUES ('7', '生活', null, null, null);
INSERT INTO `book_sort` VALUES ('8', '励志', '', null, null);
INSERT INTO `book_sort` VALUES ('9', '故事', null, null, null);
INSERT INTO `book_sort` VALUES ('10', '诗歌', null, null, null);
INSERT INTO `book_sort` VALUES ('11', '社会学', null, null, null);
INSERT INTO `book_sort` VALUES ('12', '文学', null, null, null);
INSERT INTO `book_sort` VALUES ('13', '哲学', null, null, null);
INSERT INTO `book_sort` VALUES ('14', '科学', null, null, null);
INSERT INTO `book_sort` VALUES ('16', '幽默', '', null, null);
