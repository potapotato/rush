/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : rush

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 22/07/2021 18:26:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for Chapter
-- ----------------------------
DROP TABLE IF EXISTS `Chapter`;
CREATE TABLE `Chapter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Chapter_name_uindex`(`name`) USING BTREE,
  INDEX `Chapter_Course_id_fk`(`course_id`) USING BTREE,
  CONSTRAINT `Chapter_Course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Chapter
-- ----------------------------
BEGIN;
INSERT INTO `Chapter` (`id`, `course_id`, `name`, `created_time`, `enabled`) VALUES (1, 1, '第一章', '2021-02-26 20:37:23', 1), (2, 2, '集合', '2021-07-22 17:37:29', 1), (3, 2, '数列极限', '2021-07-22 17:37:40', 1);
COMMIT;

-- ----------------------------
-- Table structure for Course
-- ----------------------------
DROP TABLE IF EXISTS `Course`;
CREATE TABLE `Course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_user_id` int(11) NOT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Course_name_uindex`(`name`) USING BTREE,
  INDEX `Course_User_id_fk`(`create_user_id`) USING BTREE,
  CONSTRAINT `Course_User_id_fk` FOREIGN KEY (`create_user_id`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Course
-- ----------------------------
BEGIN;
INSERT INTO `Course` (`id`, `name`, `description`, `img_url`, `created_time`, `create_user_id`, `enabled`) VALUES (1, 'test', 'just a test', 'courseImages/test.jpg', '2021-01-02 21:56:16', 1, 0), (2, '高数', '高数很难，真的', 'courseImages/b2686832135d466eadbc8e2342d58fc6.jpg', '2021-02-26 14:40:23', 1, 1), (3, '线性代数', '线性代数比较简单', 'courseImages/dd9f24ba3527854cf553e91620e204e7.jpeg', '2021-02-26 14:42:35', 1, 1), (4, '计算机网络', '计算机网络是一门必修科目', 'courseImages/b4844914bc3f960e4f1eaa9a48f7ac64.jpeg', '2021-02-26 14:46:58', 1, 1), (5, '抽象代数', '抽象代数（Abstract algebra）又称近世代数（Modern algebra），它产生于十九世纪。伽罗瓦〔1811-1832〕在1832年运用「群」的概念彻底解决了用根式求解代数方程的可能性问题。他是第一个提出「群」的概念的数学家，一般称他为近世代数创始人。他使代数学由作为解方程的科学转变为研究代数运算结构的科学，即把代数学由初等代数时期推向抽象代数。', 'courseImages/5c5e9e3f69b94e93490323b4c220b4ed.jpeg', '2021-02-26 20:23:00', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for Course_role
-- ----------------------------
DROP TABLE IF EXISTS `Course_role`;
CREATE TABLE `Course_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Course_role_Course_id_fk`(`course_id`) USING BTREE,
  INDEX `Course_role_Role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `Course_role_Course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Course_role_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Table structure for Permission
-- ----------------------------
DROP TABLE IF EXISTS `Permission`;
CREATE TABLE `Permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Permission
-- ----------------------------
BEGIN;
INSERT INTO `Permission` (`id`, `permission_str`) VALUES (1, '*:*:*'), (2, 'course:select:*');
COMMIT;

-- ----------------------------
-- Table structure for Question
-- ----------------------------
DROP TABLE IF EXISTS `Question`;
CREATE TABLE `Question`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `section_id` int(11) NOT NULL,
  `question_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `question_text` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `answer_text` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  `question_type_id` int(11) NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `create_user_id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Question_answer_url_uindex`(`answer_url`) USING BTREE,
  UNIQUE INDEX `Question_question_url_uindex`(`question_url`) USING BTREE,
  INDEX `Question_Section_id_fk`(`section_id`) USING BTREE,
  INDEX `Question_User_id_fk`(`create_user_id`) USING BTREE,
  INDEX `Question_Course_id_fk`(`course_id`) USING BTREE,
  INDEX `Question_Question_type_id_fk`(`question_type_id`) USING BTREE,
  CONSTRAINT `Question_Course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Question_Question_type_id_fk` FOREIGN KEY (`question_type_id`) REFERENCES `Question_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Question_Section_id_fk` FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Question_User_id_fk` FOREIGN KEY (`create_user_id`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Question
-- ----------------------------
BEGIN;
INSERT INTO `Question` (`id`, `section_id`, `question_url`, `question_text`, `answer_url`, `answer_text`, `score`, `question_type_id`, `created_time`, `create_user_id`, `enabled`, `course_id`) VALUES (1, 1, 'questionImages/question/6301f05e6361f11d5389ff57273fc99a.png', '', 'questionImages/answer/92c01ab0d77d36d180260440140dd9ac.png', '', 12, 2, '2021-02-26 20:40:44', 1, 1, 5);
COMMIT;

-- ----------------------------
-- Table structure for Question_type
-- ----------------------------
DROP TABLE IF EXISTS `Question_type`;
CREATE TABLE `Question_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `question_type_name_uindex`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Question_type
-- ----------------------------
BEGIN;
INSERT INTO `Question_type` (`id`, `name`) VALUES (2, '主观题'), (1, '选择题');
COMMIT;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Role
-- ----------------------------
BEGIN;
INSERT INTO `Role` (`id`, `name`) VALUES (1, 'admin'), (2, 'common'), (3, 'anon');
COMMIT;

-- ----------------------------
-- Table structure for Role_permission
-- ----------------------------
DROP TABLE IF EXISTS `Role_permission`;
CREATE TABLE `Role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `Role_permission_Permission_id_fk`(`permission_id`) USING BTREE,
  INDEX `Role_permission_Role_id_fk`(`role_id`) USING BTREE,
  CONSTRAINT `Role_permission_Permission_id_fk` FOREIGN KEY (`permission_id`) REFERENCES `Permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Role_permission_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Role_permission
-- ----------------------------
BEGIN;
INSERT INTO `Role_permission` (`id`, `role_id`, `permission_id`) VALUES (1, 1, 1), (2, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for Section
-- ----------------------------
DROP TABLE IF EXISTS `Section`;
CREATE TABLE `Section`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chapter_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `Section_name_uindex`(`name`) USING BTREE,
  INDEX `Section_Chapter_id_fk`(`chapter_id`) USING BTREE,
  INDEX `Section_Course_id_fk`(`course_id`) USING BTREE,
  CONSTRAINT `Section_Chapter_id_fk` FOREIGN KEY (`chapter_id`) REFERENCES `Chapter` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `Section_Course_id_fk` FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of Section
-- ----------------------------
BEGIN;
INSERT INTO `Section` (`id`, `name`, `chapter_id`, `course_id`, `created_time`, `enabled`) VALUES (1, '第一节', 1, 1, '2021-02-26 20:37:52', 1), (2, '函数定义', 3, 2, '2021-07-22 17:43:07', 1), (3, '集合定义', 2, 2, '2021-07-22 17:43:21', 1);
COMMIT;

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `salt` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `icon_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `created_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `User_nickname_uindex`(`nickname`) USING BTREE,
  UNIQUE INDEX `User_username_uindex`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO `User` (`id`, `username`, `password`, `nickname`, `salt`, `icon_url`, `created_time`, `email`, `enabled`) VALUES (1, 'jqq', 'd35f4be164f06e5c0a3d7102d55f43f7', 'jqqya', 'IfAxNrJ4', 'userIcon/024a026c8ad5640bd48b8e015ae263a4.jpeg', '2020-12-31 15:39:24', '1158612615@qq.com', 1), (2, 'admin', 'cab2e4e9c91102e630beed0932c6fc1c', 'admin', '9%NPLsII', '', '2020-12-31 15:49:45', '1158612615@qq.com', 1), (3, 'test', 'd1825d19198a3ea9fe314cfd67b95ca1', 'test', 'SBrLq!y3', '', '2020-12-31 15:50:21', 'test@qq.com', 1), (4, 'a', '3bf08848890e9488315685a22a1dd1c9', 'a', 'SZJFKHgd', '', '2020-12-31 15:52:46', '1158612615@qq.com', 0), (5, 'bbb', '76b0926a6d1eab191f85ca9d7076bc27', 'bbb', '0)K*Sd8w', '', '2020-12-31 16:01:49', '1158612615@qq.com', 0), (6, 'hxx', '495cdc624dc07fff207663420ed759f0', 'waxijiang', '@5J!Jd)E', 'userIcon/1314b1159779339216e7d81bdf68619f.jpg', '2021-02-27 20:48:57', '1158612615@qq.com', 1);
COMMIT;

-- ----------------------------
-- Table structure for User_role
-- ----------------------------
DROP TABLE IF EXISTS `User_role`;
CREATE TABLE `User_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `User_role_Role_id_fk`(`role_id`) USING BTREE,
  INDEX `User_role_User_id_fk`(`user_id`) USING BTREE,
  CONSTRAINT `User_role_Role_id_fk` FOREIGN KEY (`role_id`) REFERENCES `Role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `User_role_User_id_fk` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci;

-- ----------------------------
-- Records of User_role
-- ----------------------------
BEGIN;
INSERT INTO `User_role` (`id`, `user_id`, `role_id`) VALUES (1, 1, 1), (2, 2, 2), (3, 3, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
