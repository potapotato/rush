/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 127.0.0.1:3306
 Source Schema         : rush

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 26/02/2021 21:28:38
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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Permission
-- ----------------------------
DROP TABLE IF EXISTS `Permission`;
CREATE TABLE `Permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_str` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Question_type
-- ----------------------------
DROP TABLE IF EXISTS `Question_type`;
CREATE TABLE `Question_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `question_type_name_uindex`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for Role
-- ----------------------------
DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
