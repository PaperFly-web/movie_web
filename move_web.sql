/*
 Navicat Premium Data Transfer

 Source Server         : HJC
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : move_web

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 06/02/2021 21:40:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for approve_move
-- ----------------------------
DROP TABLE IF EXISTS `approve_move`;
CREATE TABLE `approve_move`  (
  `approve_m_is_pass` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `approve_m_create_time` datetime(0) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `approve_m_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '暂时没有解释说明!',
  `approve_m_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `m_id` bigint(20) NOT NULL,
  PRIMARY KEY (`approve_m_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 130 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for approve_suggest
-- ----------------------------
DROP TABLE IF EXISTS `approve_suggest`;
CREATE TABLE `approve_suggest`  (
  `approve_c_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `approve_c_create_time` datetime(0) NOT NULL,
  `approve_c_statues` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  `c_id` bigint(20) NOT NULL,
  `approve_c_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '暂时没有解释说明!',
  PRIMARY KEY (`approve_c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `c_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `m_id` bigint(20) NOT NULL,
  `c_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `c_create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for move
-- ----------------------------
DROP TABLE IF EXISTS `move`;
CREATE TABLE `move`  (
  `m_is_pass` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `m_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `m_size` double NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `m_hot` bigint(20) NOT NULL DEFAULT 0,
  `m_false_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_direct` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '不详',
  `m_story` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '暂无',
  `m_area` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '不详',
  `m_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '不详',
  `m_main_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '不详',
  `m_public_time` datetime(0) NULL DEFAULT NULL,
  `m_false_pic_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_pic_save_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_duration` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `m_create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 95 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for suggest
-- ----------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest`  (
  `succ_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `succ_user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `succ_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `succ_create_time` datetime(0) NOT NULL,
  `succ_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`succ_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_pwd` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `user_perm` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0',
  `user_create_time` datetime(0) NULL DEFAULT NULL,
  `user_last_login_time` datetime(0) NULL DEFAULT NULL,
  `user_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_phone`(`user_phone`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for view
-- ----------------------------
DROP TABLE IF EXISTS `view`;
CREATE TABLE `view`  (
  `v_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `m_id` bigint(20) NOT NULL,
  `v_create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`v_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 151 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Procedure structure for add_user
-- ----------------------------
DROP PROCEDURE IF EXISTS `add_user`;
delimiter ;;
CREATE PROCEDURE `add_user`(in  userName VARCHAR(255),in userPhone VARCHAR(255) ,in  userPwd VARCHAR(255) ,in  userRole VARCHAR(255),
in  userPerm VARCHAR(255),in userCreateTime  datetime(2))
  SQL SECURITY INVOKER
begin
		
    SELECT COUNT(*) into @isExist  FROM `user` WHERE user_phone=userPhone;
if(@isExist=0)
THEN
insert into `user`( user_name, user_phone, user_pwd, user_role, user_perm, user_create_time)
VALUES(userName,userPhone,userPwd,userRole,userPerm,userCreateTime);
ELSE
SELECT 0;
end if;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for approveMove
-- ----------------------------
DROP PROCEDURE IF EXISTS `approveMove`;
delimiter ;;
CREATE PROCEDURE `approveMove`(in approveMIsPass VARCHAR(2) ,in  approveMCreateTime datetime(2),
			in  adminId VARCHAR(255) ,in  aproveMNote VARCHAR(255),in  mId VARCHAR(255))
  SQL SECURITY INVOKER
begin
		
	SELECT count(*) into @mIsExist FROM move WHERE move.m_id=mId;
   if(@mIsExist>0)
THEN
UPDATE move set move.m_is_pass=approveMIsPass WHERE move.m_id=mId;
INSERT into approve_move(approve_m_is_pass, approve_m_create_time, admin_id, approve_m_note, m_id)
VALUES(approveMIsPass,approveMCreateTime,adminId,aproveMNote,mId);
ELSE
SELECT @mIsExist;
end if;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for find_movie_by_keywords
-- ----------------------------
DROP PROCEDURE IF EXISTS `find_movie_by_keywords`;
delimiter ;;
CREATE PROCEDURE `find_movie_by_keywords`(in keywords VARCHAR(60) ,in page BIGINT)
  SQL SECURITY INVOKER
begin
		SELECT *,(
					select count(*) as count from move where 
		m_name like CONCAT('%',keywords,'%') 
	or m_main_role like CONCAT('%',keywords,'%') 
	or m_direct like CONCAT('%',keywords,'%') 
	or m_area like CONCAT('%',keywords,'%')
	 or m_type like CONCAT('%',keywords,'%') 
	or m_story like CONCAT('%',keywords,'%')
and m_is_pass='1'
) as count 
 FROM (
	select move.*,user.user_name from move,user where 
(m_name like CONCAT('%',keywords,'%') 
	or m_main_role like CONCAT('%',keywords,'%') 
	or m_direct like CONCAT('%',keywords,'%') 
	or m_area like CONCAT('%',keywords,'%')
	 or m_type like CONCAT('%',keywords,'%') 
	or m_story like CONCAT('%',keywords,'%'))
	 and move.user_id=user.user_id 
and m_is_pass='1'
	order by m_hot desc,m_public_time desc,m_create_time desc 
	limit page,12) t;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for login
-- ----------------------------
DROP PROCEDURE IF EXISTS `login`;
delimiter ;;
CREATE PROCEDURE `login`(in userPhone VARCHAR(255) ,in  userPwd VARCHAR(255) ,in  lastLoginTime datetime(2))
  SQL SECURITY INVOKER
begin
		set @userStatus='0';
		SELECT `user`.user_status into @userStatus FROM `user` WHERE user_phone=userPhone
and move_web.user.user_pwd=userPwd;
if(@userStatus='1')THEN
   UPDATE `user` SET user_last_login_time=lastLoginTime  WHERE user_phone=userPhone;
end if;
select * from user where move_web.user.user_phone=userPhone
        and move_web.user.user_pwd=userPwd;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for modify_user_info
-- ----------------------------
DROP PROCEDURE IF EXISTS `modify_user_info`;
delimiter ;;
CREATE PROCEDURE `modify_user_info`(in userId BIGINT ,in userName VARCHAR(40),in userPhone VARCHAR(11),in userPwd VARCHAR(60))
  SQL SECURITY INVOKER
begin
	SELECT user_pwd into @temUserPwd FROM user WHERE user_id=userId;
if(userPwd=@temUserPwd)
THEN
UPDATE user set user_name=userName,user_phone=userPhone WHERE user_id=userId;
ELSE
UPDATE user set user_name=userName,user_phone=userPhone,user_pwd=userPwd WHERE user_id=userId;
end if;
end
;;
delimiter ;

-- ----------------------------
-- Procedure structure for view_move_history
-- ----------------------------
DROP PROCEDURE IF EXISTS `view_move_history`;
delimiter ;;
CREATE PROCEDURE `view_move_history`(in mId BIGINT,in userId BIGINT,in vCreateTime datetime(2))
  SQL SECURITY INVOKER
begin
		
	SELECT count(*) into @mIsExist FROM move WHERE move.m_id=mId;
   if(@mIsExist>0)
THEN
update move_web.move set m_hot=m_hot+1 where m_id=mId;
INSERT into view(m_id,user_id,v_create_time) VALUES(mId,userId,vCreateTime);
ELSE
SELECT @mIsExist;
end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
