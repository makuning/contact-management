# 删除存在的表
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS mail;
DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS `group`;
DROP TABLE IF EXISTS `user`;


# 创建表
CREATE TABLE `user` (
	id CHAR(32) PRIMARY KEY COMMENT '关键字',
	username VARCHAR(10) UNIQUE COMMENT '用户名',
	`password` VARCHAR(20) COMMENT '登录密码',
	contact_id CHAR(32) COMMENT '外键，关联联系人，表示自己的信息',
	is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除'
) COMMENT = '用户表';

CREATE TABLE `group` (
	id CHAR(32) PRIMARY KEY COMMENT '关键字',
	`name` VARCHAR(10) COMMENT '组名',
	user_id CHAR(32) COMMENT '外键，关联用户',
	is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除',
	FOREIGN KEY (user_id) REFERENCES `user`(id)
) COMMENT = '分组表';

CREATE TABLE contact (
	id CHAR(32) PRIMARY KEY COMMENT '关键字',
	`name` VARCHAR(10) COMMENT '联系人姓名',
	address VARCHAR(50) COMMENT '联系人居住地址',
	remark VARCHAR(255) COMMENT '联系人备注',
	user_id CHAR(32) COMMENT '外键，关联用户',
	group_id CHAR(32) COMMENT '外键，关联分组',
	is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除',
	FOREIGN KEY (user_id) REFERENCES `user`(id),
	FOREIGN KEY (group_id) REFERENCES `group`(id)
) COMMENT = '联系人表';

CREATE TABLE mail (
	id CHAR(32) PRIMARY KEY COMMENT '关键字',
	`value` VARCHAR(255) COMMENT '邮箱地址',
	contact_id CHAR(32) COMMENT '外键，关联联系人',
	is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除',
	FOREIGN KEY (contact_id) REFERENCES contact(id)
) COMMENT = '邮箱表';

CREATE TABLE phone (
	id CHAR(32) PRIMARY KEY COMMENT '关键字',
	`value` CHAR(11) COMMENT '电话号码',
	source VARCHAR(20) COMMENT '号码归属地',
	contact_id CHAR(32) COMMENT '外键，关联联系人',
	is_deleted TINYINT(1) DEFAULT 0 COMMENT '逻辑删除',
	FOREIGN KEY (contact_id) REFERENCES contact(id)
) COMMENT = '电话表';

# 为用户表关联外键
ALTER TABLE `user`
ADD CONSTRAINT fk_user_contact
FOREIGN KEY (contact_id)
REFERENCES contact(id);
	