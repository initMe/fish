/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : fish

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-05-19 13:36:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'blog主键',
  `title` varchar(32) NOT NULL COMMENT 'blog标题',
  `cate_code` int(2) NOT NULL COMMENT '所属分类',
  `brief` varchar(50) DEFAULT NULL COMMENT '简介',
  `title_pic` varchar(50) DEFAULT NULL COMMENT '封面图片',
  `author_id` bigint(11) NOT NULL COMMENT '作者id',
  `keywords` varchar(20) DEFAULT NULL COMMENT '关键词',
  `content` mediumtext NOT NULL COMMENT '内容',
  `is_top` int(1) NOT NULL DEFAULT 0 COMMENT '是否置顶',
  `click_count` int(5) NOT NULL DEFAULT 0 COMMENT '点击量',
  `reply_count` int(5) NOT NULL DEFAULT 0 COMMENT '评论数',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `creator` varchar(20) NOT NULL COMMENT '创建者',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `modifier` varchar(20) NOT NULL COMMENT '修改者',
  `gmt_modify` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='blog表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(20) NOT NULL COMMENT '账户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `login_count` int(5) NOT NULL DEFAULT 0 COMMENT '登录次数',
  `last_login_time` datetime COMMENT '上次登录时间',
  `headimg` varchar(50) DEFAULT NULL COMMENT '头像',
  `signature` varchar(200) DEFAULT NULL COMMENT '签名',
  `wx_openid` varchar(32) DEFAULT NULL COMMENT '微信id',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) unsigned NOT NULL COMMENT '用户id',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `birthday` date COMMENT '生日',
  `province` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `distirct` varchar(32) DEFAULT NULL COMMENT '区/县',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息扩展表';

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `blog_id` bigint(11) unsigned NOT NULL COMMENT '文章id',
  `user_id` bigint(11) unsigned NOT NULL COMMENT '点赞人id',
  `content` mediumtext NOT NULL COMMENT '',
  `status` int(1) NOT NULL DEFAULT 0 COMMENT '状态',
  `agree_count` int(5) NOT NULL DEFAULT 0 COMMENT '点赞数量',
  `creator` varchar(20) NOT NULL COMMENT '创建者',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modify` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='评论表';

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `name` varchar(32) NOT NULL COMMENT '分类名称',
  `description` varchar(120) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` bigint(11) unsigned NOT NULL COMMENT '文章id',
  `cate_id` bigint(11) unsigned NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章分类对应表';

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(32) NOT NULL COMMENT '标签名称',
  `description` varchar(120) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签表';

-- ----------------------------
-- Table structure for blog_label
-- ----------------------------
DROP TABLE IF EXISTS `blog_label`;
CREATE TABLE `blog_label` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blog_id` bigint(11) unsigned NOT NULL COMMENT '文章id',
  `label_id` bigint(11) unsigned NOT NULL COMMENT '标签id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章标签对应表';